##FileBox
Utility App
```java
public static void makeJsonObjectRequest(int method, Context context, final JSONObject params, String url, final int rid, final VolleyResponseListener listener) {
 //JsonObjectRequest(int method, String url, JSONObject jsonRequest, Listener<JSONObject> listener, ErrorListener errorListener)
 // JsonObjectRequest(String url, JSONObject jsonRequest, Listener<JSONObject> listener, ErrorListener errorListener)


 JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(method, url, (params), new Response.Listener < JSONObject > () {

  @Override
  public void onResponse(JSONObject response) {
   listener.onResponse(response, rid);
  }
 }, new Response.ErrorListener() {


  @Override
  public void onErrorResponse(VolleyError error) {
   listener.onError(error.toString());
  }
 }) {
  @Override
  public Map < String, String > getHeaders() throws AuthFailureError {
   Map < String, String > header = new HashMap < String, String > ();
   header.put("Accept", "application/json");
   header.put("Content-Type", "application/json");
   return header;
  }


  @Override
  protected Response < JSONObject > parseNetworkResponse(NetworkResponse response) {
   try {
    String jsonString = new String(response.data,
     HttpHeaderParser.parseCharset(response.headers, PROTOCOL_CHARSET));
    return Response.success(new JSONObject(jsonString),
     HttpHeaderParser.parseCacheHeaders(response));
   } catch (UnsupportedEncodingException e) {
    return Response.error(new ParseError(e));
   } catch (JSONException je) {
    return Response.error(new ParseError(je));
   }
  }


 };

 jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
  MY_SOCKET_TIMEOUT_MS,
  DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
  DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
 // Access the RequestQueue through singleton class.
 MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
 // Access the RequestQueue through singleton class.
 //MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
}

```
