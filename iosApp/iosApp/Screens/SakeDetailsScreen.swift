import SwiftUI
import MapKit
import Shared

struct SakeDetailsScreen: View {
    var sakeLocation: SakeLocation

    var body: some View {
        VStack(alignment: .leading, spacing: 16) {
            Text("Name: \(sakeLocation.name)")
                .font(.largeTitle)
                .bold()
            if let pictureUrl = sakeLocation.picture, let url = URL(string: pictureUrl) {
                AsyncImage(url: url) { phase in
                    switch phase {
                    case .empty:
                        ProgressView()
                            .frame(height: 200)
                    case .success(let image):
                        image
                            .resizable()
                            .scaledToFill()
                            .frame(height: 200)
                            .clipped()
                            .cornerRadius(12)
                    case .failure(_):
                        Image(systemName: "photo.fill")
                            .resizable()
                            .scaledToFit()
                            .frame(height: 200)
                            .foregroundColor(.gray)
                            .background(Color.gray.opacity(0.2))
                            .cornerRadius(12)
                    @unknown default:
                        EmptyView()
                    }
                }
            } else {
                Image(systemName: "photo.fill")
                    .resizable()
                    .scaledToFit()
                    .frame(height: 200)
                    .foregroundColor(.gray)
                    .background(Color.gray.opacity(0.2))
                    .cornerRadius(12)
            }
            Text("Description: \(sakeLocation.description_)")
            Button {
                openInAppleMaps()
            } label: {
                Label(sakeLocation.address, systemImage: "mappin.and.ellipse")
                    .foregroundColor(.blue)
                    .underline()
            }
            Text("Rating: \(sakeLocation.formattedRating())")
                .frame(maxWidth: .infinity, alignment: .trailing)
            
            if let url = URL(string: sakeLocation.website) {
                Link(destination: url) {
                    Label("Visit Website", systemImage: "globe")
                        .padding()
                        .background(Color.blue)
                        .foregroundColor(.white)
                        .cornerRadius(8)
                        .frame(maxWidth: .infinity, alignment: .trailing)
                }
            }
            Spacer()
        }
        .padding()
        .navigationTitle("Sake Details")
        .navigationBarTitleDisplayMode(.inline)
    }
    
    private func openInAppleMaps() {
        guard sakeLocation.coordinates.count == 2 else { return }
        let latitude = sakeLocation.coordinates[0]
        let longitude = sakeLocation.coordinates[1]
        let placemark = MKPlacemark(coordinate: CLLocationCoordinate2D(
            latitude: CLLocationDegrees(truncating: latitude),
            longitude: CLLocationDegrees(truncating: longitude)
        ))
        let mapItem = MKMapItem(placemark: placemark)
        mapItem.name = sakeLocation.name
        mapItem.openInMaps(launchOptions: nil)
    }
}
