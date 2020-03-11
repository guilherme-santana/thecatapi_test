package bodyFactory;


import org.json.JSONException;
import org.json.simple.JSONObject;


public class BodyFavourites {

	@SuppressWarnings("unchecked")
	public static JSONObject bodyFavourites() {
		JSONObject favourites = new JSONObject();
		try {
			favourites.put("image_id", "img003");
			favourites.put("sub_id", "user01");

		} catch(JSONException e) {
			e.printStackTrace();
		}
		return favourites;
	}
	
	@SuppressWarnings("unchecked")
	public static JSONObject bodyFavouritesSemImagem() {
		JSONObject votes = new JSONObject();
		try {
			votes.put("sub_id", "user01");

		} catch(JSONException e) {
			e.printStackTrace();
		}
		return votes;
	}



}
