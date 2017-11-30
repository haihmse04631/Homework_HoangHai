package com.example.haihm.demologin;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestAsyncTask;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.LoggingBehavior;
import com.facebook.Profile;
import com.facebook.login.Login;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    LoginButton loginButton;
    CallbackManager callbackManager;
    String name, avatar, cover, id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_main);

        loginButton = (LoginButton) findViewById(R.id.login_button);

        loginButton.setReadPermissions(Arrays.asList("public_profile", "email"));

        login();
        logout();

    }

    private void logout() {
    }

    private void login() {
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                getInfor();
                //loginButton.setVisibility(View.INVISIBLE);
                Toast.makeText(MainActivity.this, "Login Successfull!", Toast.LENGTH_LONG);
            }

            @Override
            public void onCancel() {
                
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(MainActivity.this, "No Internet!", Toast.LENGTH_LONG);
            }
        });
    }

    private void getInfor() {
        Bundle params = new Bundle();
        params.putString("fields", "id,name,cover");
        GraphRequestAsyncTask graphRequestAsyncTask = new GraphRequest(AccessToken.getCurrentAccessToken(), "me", params, HttpMethod.GET,
                new GraphRequest.Callback() {
                    @Override
                    public void onCompleted(GraphResponse response) {
                        if (response != null) {
                            String userDetail = response.getRawResponse();
                            FacebookSdk.addLoggingBehavior(LoggingBehavior.REQUESTS);
                            try {
                                JSONObject jsonObject = new JSONObject(userDetail);
                                Log.e("object", jsonObject.toString());
                                id = jsonObject.getString("id");
                                name = jsonObject.getString("name");
                                avatar = "https://graph.facebook.com/" + id + "/picture?width=960&height=960";
                                if (jsonObject.has("cover")) {
                                    String getInitialCover = jsonObject.getString("cover");

                                    if (getInitialCover.equals("null")) {
                                        jsonObject = null;
                                    } else {
                                        JSONObject JOCover = jsonObject.optJSONObject("cover");

                                        if (JOCover.has("source")) {

                                            cover = JOCover.getString("source");
                                        } else {
                                            cover = null;
                                        }
                                    }
                                } else {
                                    cover = null;
                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("id", id);
                        bundle.putString("name", name);
                        bundle.putString("cover", cover);
                        bundle.putString("avatar", avatar);
                        intent.putExtra("myPakage", bundle);
                        startActivityForResult(intent, 0);
                    }
                }).executeAsync();
    }

//    private void getInfor() {
//        GraphRequest graphRequest = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
//            @Override
//            public void onCompleted(JSONObject object, GraphResponse response) {
//                Log.e("JSON", response.getJSONObject().toString());
//                try {
//                    name = object.getString("name");
//                    profilePictureView.setProfileId(object.getString("id"));
//
//                    tvName.setText(name);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        Bundle parameters = new Bundle();
//        parameters.putString("fields", "name, email");
//        graphRequest.setParameters(parameters);
//        graphRequest.executeAsync();
//    }



    @Override
    protected void onStart() {
        LoginManager.getInstance().logOut();
        super.onStart();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
