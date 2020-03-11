package bodyFactory;


import org.json.JSONException;
import org.json.simple.JSONObject;


public class BodyVotes {

	@SuppressWarnings("unchecked")
	public static JSONObject bodyVotes() {
		JSONObject votes = new JSONObject();
		try {
			votes.put("image_id", "img003");
			votes.put("sub_id", "user01");
			votes.put("value", 1);

		} catch(JSONException e) {
			e.printStackTrace();
		}
		return votes;
	}
	
	@SuppressWarnings("unchecked")
	public static JSONObject bodyVotesSemValue() {
		JSONObject votes = new JSONObject();
		try {
			votes.put("image_id", "img001");
			votes.put("sub_id", "user01");

		} catch(JSONException e) {
			e.printStackTrace();
		}
		return votes;
	}



}
