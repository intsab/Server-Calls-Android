# Network Calls
Server Calls is a free Library for Http POST and GET requests for Android. You can upload file also in very simple way. 

<h1>USAGE</h1>
Step 1- Simple Download the library folder </br>
Step 2- Import in your project as module </br>
step 3- Add  ``` android {
    useLibrary 'org.apache.http.legacy' 
    } ``` in your gradle file of project. (if not exist*)</br>
<h1>Post Request</h1>
 ```
 ArrayList<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("username", "10"));
        params.add(new BasicNameValuePair("password", "0"));
        NetworkCalls.getInstance().postRequest("http://taazi.com/api/logInMobile/format/json", params, new NetworkCalls.ResponseListener() {
            @Override
            public void onResponse(final String response) {
                Log.d("", response);
                MainActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        Log.d("Response:",response);
                    }
                });

            }

            @Override
            public void onErrorResponse(String error) {
                Log.d("Error:",error);
            }

        });
```
<h1>GET Request</h1>
 ```
  NetworkCalls.getInstance().getRequest("http://tickets.taazi.com/api/getEvents/?limit=10&offset=0", new NetworkCalls.ResponseListener() {
            @Override
            public void onResponse(final String response) {
                Log.d("", response);
                MainActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        Log.d("Response:",response);
                    }
                });
            }

            @Override
            public void onErrorResponse(String error) {
                Log.d("Error:",error);
            }
        });
 ```
 <h1>Upload File with Parameters Request</h1>
 ```
  ArrayList<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("token", "22c61949efb02fd66c446f24dc680078"));
        params.add(new BasicNameValuePair("type", "profile"));
        File file = new File("/storage/sdcard0/DCIM/Camera/img.jpg");
        NetworkCalls.getInstance().uploadFileRequest("http://taazi.com/api/profilePic/format/json", file, "profile_pic", params, new NetworkCalls.ResponseListener() {
            @Override
            public void onResponse(String response) {
               Log.d("Response:",response);
            }

            @Override
            public void onErrorResponse(String error) {
                Log.d("Error:",error);
            }
        });
 ```
 <h1>Developers</h1>
 1- Intsab Haider (Mobile Application Developer)</br>
 2- Uferah Shafi (Android Application developer)
