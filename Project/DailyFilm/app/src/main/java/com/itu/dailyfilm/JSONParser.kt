package com.itu.dailyfilm

import com.itu.dailyfilm.Models.Film
import com.itu.dailyfilm.Models.Ratings
import org.json.JSONObject


object JSONParser {
    fun retObjectFromJSON(response:String): Film {
        // constructor without arguments
        val film = Film()
        val responseJSON = JSONObject(response)
        //Check for existence of Title key.
        if (responseJSON.has("Title"))
            film.title = responseJSON.getString("Title")

        if (responseJSON.has("Year"))
            film.year = (responseJSON.get("Year") as String).toInt()

        if (responseJSON.has("Plot"))
            film.plot = responseJSON.getString("Plot")

        if (responseJSON.has("imdbID"))
            film.imdbID = responseJSON.getString("imdbID")

        if (responseJSON.has("Runtime"))
            film.runtime = responseJSON.getString("Runtime")

        if(responseJSON.has("Ratings")) {
            val ratingsJSON = responseJSON.getJSONArray("Ratings")
            for (i in 0 until ratingsJSON.length()){
                // Get JSON object given by index
                val ratingJSON = ratingsJSON.getJSONObject(i)
                // constructor without arguments
                val rating = Ratings()
                if (ratingJSON.has("Source"))
                    rating.source = ratingJSON.getString("Source")
                if (ratingJSON.has("Value"))
                    rating.value = ratingJSON.getString("Value")
                film.ratings.add(rating)
            }
        }
        return film
    }
}