package com.test.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.test.myapplication.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

/**
 * Created by NehaRege on 8/18/16.
 */
public class LoginActivity extends FragmentActivity {

    private String TAG = "LoginActivity";

    private LoginButton loginButton;
    private TextView textViewName;
    private TextView textViewEmail;

    private CallbackManager callbackManager;

    private String email;
    private String name;
    private String firstName;
    private String lastName;
    private String id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_login);

        FacebookSdk.sdkInitialize(getApplicationContext());
//        AppEventsLogger.activateApp(getApplication(),"1813279535570044");

        callbackManager = CallbackManager.Factory.create();

        loginButton = (LoginButton) findViewById(R.id.login_button);

//        loginButton.setFragment(LoginActivity.this);

        textViewName = (TextView) findViewById(R.id.fb_name);
        textViewEmail = (TextView) findViewById(R.id.fb_email);

//        loginButton.setFragment(this);

        loginButton.setReadPermissions(Arrays.asList("public_profile","email"));


        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

//                textViewName.setText(loginResult.getAccessToken().getUserId());

                GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {

                                try {

//                                    Log.i(TAG, "onCompleted: "+response.getError().getErrorCode());
//                                    Log.i(TAG, "onCompleted: "+ response.getError().getErrorMessage());

                                    email = object.getString("email");
//                                    firstName = object.getString("firstName");
//                                    lastName = object.getString("lastName");
                                    name = object.getString("name");
//                                    id = object.getString("id");
//                                    textViewName.setText(name);
//                                    textViewEmail.setText(email);

                                    Toast.makeText(LoginActivity.this, "Logged in as: "+email, Toast.LENGTH_SHORT).show();

                                    loginButton.setVisibility(View.INVISIBLE);

//                                    LoginManager.getInstance().logOut();

                                    finish();

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        });


                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email");
                graphRequest.setParameters(parameters);
                graphRequest.executeAsync();

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode,resultCode,data);

    }


}
