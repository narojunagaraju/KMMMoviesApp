//
//  DetailsScreen.swift
//  iosApp
//
//  Created by Nagaraju Naroju on 07/07/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct DetailsScreen: View {
    let movie:Movie
    
    var body: some View {
        ScrollView{
            VStack{
                ZStack{
                    AsyncImage(url: URL(string: movie.imageUrl)) { image in
                        image.resizable().scaledToFill()
                    }placeholder: {
                        ProgressView()
                    }
                }.frame(maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/,minHeight: 300,maxHeight: 300)
                
                VStack(alignment: .leading, spacing: 12) {
                    Text(movie.title)
                        .font(/*@START_MENU_TOKEN@*/.title/*@END_MENU_TOKEN@*/)
                        .fontWeight(/*@START_MENU_TOKEN@*/.bold/*@END_MENU_TOKEN@*/)
                        .fixedSize(horizontal: false, vertical: /*@START_MENU_TOKEN@*/true/*@END_MENU_TOKEN@*/)
                    
                    Button(action:{}){
                        HStack {
                            Image(systemName: "play.fill")
                                .foregroundColor(.black)
                            Text("Start Watching Now..")
                                .foregroundColor(.black)
                        }
                        
                    }.frame(maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/,maxHeight: 40)
                        .padding()
                        .background(.red)
                        .clipShape(RoundedRectangle(cornerRadius: 8))
                    
                    Text("Released in \(movie.releaseDate)".uppercased())
                    
                    Text(movie.description)
                        .font(.body)
                        .fixedSize(horizontal: false, vertical: /*@START_MENU_TOKEN@*/true/*@END_MENU_TOKEN@*/)
                }
                .padding(20)
                .background(.black)
            }.frame(maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/,maxHeight: .infinity)
        }
    }
}

struct DetailsScreen_Previews:PreviewProvider {
    static var previews: some View {
        DetailsScreen(movie: sampleMovie)
    }
}
