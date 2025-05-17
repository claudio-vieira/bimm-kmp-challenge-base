import SwiftUI
import Shared

extension SakesScreen {
    
    @MainActor
    class SakeLocationsSharedViewModelWrapper: ObservableObject {
        
        let sakeLocationsSharedViewModel: SakeLocationsSharedViewModel
        
        init() {
            sakeLocationsSharedViewModel = SakeLocationInjector().sakeLocationsSharedViewModel
            sakeLocationsState = sakeLocationsSharedViewModel.sakeLocationState.value
        }
        
        @Published var sakeLocationsState: [SakeLocation]
        
        func startObserving() {
            Task {
                for await sakeLoc in sakeLocationsSharedViewModel.sakeLocationState {
                    self.sakeLocationsState = sakeLoc
                }
            }
        }
    }
}

struct SakesScreen: View {
    
    @ObservedObject private(set) var viewModel: SakeLocationsSharedViewModelWrapper
    var onSakeSelected: (SakeLocation) -> Void
    
    var body: some View {
        VStack {
            AppBar()
            
            if(!viewModel.sakeLocationsState.isEmpty) {
                ScrollView {
                    LazyVStack(spacing: 10) {
                        ForEach(viewModel.sakeLocationsState, id: \.self) { sakeLocation in
                            SakeItemView(sakeLocation: sakeLocation) {
                                onSakeSelected(sakeLocation)
                            }
                        }
                    }
                }
            }
            
        }.onAppear{
            self.viewModel.startObserving()
        }
    }
}

private struct AppBar: View {
    var body: some View {
        Text("Sake Locations")
            .font(.largeTitle)
            .fontWeight(.bold)
    }
}

struct SakeItemView: View {
    var sakeLocation: SakeLocation
    var onTap: () -> Void
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            Text("Name: \(sakeLocation.name)")
                .font(.title)
                .fontWeight(.bold)
            Text("Address: \(sakeLocation.address)")
            Text(sakeLocation.formattedRating())
                .frame(maxWidth: .infinity, alignment: .trailing)
        }
        .padding(16)
        .background(Color(.systemBackground))
        .onTapGesture {
            onTap()
        }
    }
}

extension SakeLocation {
    func formattedRating() -> String {
        return "⭐ \(rating)"
    }
}
