package com.cloudvsnow.ltweather.Logic.model

import com.google.gson.annotations.SerializedName

data class PlaceResponse(val status: String, val places: List<Place>)

data class Place(val name: String, val location: Location, @SerializedName("formatted_address") val address: String)

data class Location(val lat: String, val lng: String)

/*
{
"status":"ok","query":" ",
"places":[ {"name":" ","location":{"lat":39.9041999,"lng":116.4073963}, "formatted_address":" "},
           {"name":" ","location":{"lat":39.89491,"lng":116.322056}, "formatted_address":" 118 "},
           {"name":" ","location":{"lat":39.865195,"lng":116.378545}, "formatted_address":" 12 "},
           {"name":" ( )","location":{"lat":39.904983,"lng":116.427287}, "formatted_address":" 2 "}]
}
 */