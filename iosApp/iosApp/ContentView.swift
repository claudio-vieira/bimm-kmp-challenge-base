import SwiftUI
import Shared

struct ContentView: View {
    
    @State private var path = NavigationPath()
    
    var body: some View {
        NavigationStack(path: $path) {
            //sakesScreen
            SakesScreen(viewModel: .init(), onSakeSelected: {
                selectedSakeLocation in
                path.append(selectedSakeLocation)
            })
                .navigationDestination(for: SakeLocation.self) { sake in
                    SakeDetailsScreen(sakeLocation: sake)
                }
                .toolbar {
                    ToolbarItemGroup {

                    }
                }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
