package com.schoolmanagement.Other;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.Html;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    //Single Instance object
    private static Utils instance = null;
    private static final String TAG = "mytag";

    //
    private Utils() {
    }

    //Single Instance get
    public static Utils getInstance() {
        if (instance == null)
            instance = new Utils();

        return instance;
    }

    /**
     * @return General Method For Debug Call from Anywhere in Application
     */
//    private static final String TAG = "com.stegowl.DDS";

//    public void d(String message) {
//        Log.i(TAG, message);
//    }

    public void toast(Activity caller, String message) {
        Toast.makeText(caller, message, Toast.LENGTH_SHORT).show();
    }

    public boolean isValidEmail(String emailId) {

        if (!TextUtils.isEmpty(emailId) && Patterns.EMAIL_ADDRESS.matcher(emailId).matches())
            return true;
        else
            return false;
    }

    public String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
    public String getRealPathFromURI2(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri,  proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public String date_time() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("MMM-dd HH:mm", Locale.ENGLISH);
        String formattedDate = df.format(c.getTime());
        return formattedDate;
    }

    private Boolean isValidPassword(String pass){
        final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[@#$%!]).{6,20})";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        if (!pass.isEmpty() && pattern.matcher(pass).matches())
            return true;
        else
            return false;
    }

//    public void loadImageCircle(Context context, CircleImageView iv, String url) {
//        try {
//            Glide.with(context)
//                    .load(url)
//
//                    .placeholder(context.getResources().getDrawable(R.mipmap.ic_launcher))
//                    .error(context.getResources().getDrawable(R.mipmap.ic_launcher))
//                    .into(iv);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    public void loadImage(Context context, ImageView iv, String url) {
//        try {
//            Picasso.with(context)
//                    .load(url)
//                    //.placeholder(context.getResources().getDrawable(R.drawable.smart_profile_bg))
//                    //.error(context.getResources().getDrawable(R.drawable.smart_profile_bg))
//                    .into(iv);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    public void loadGlide(Context context, ImageView iv, String url) {
//        try {
//            Glide.with(context)
//                    .load(url)
//                    .fitCenter()
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
////                    .centerCrop()
////                    .placeholder(R.drawable.loading_spinner)
//                    .crossFade()
//                    .into(iv);
//
//
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//    }

    public boolean isValidAmount(String val) {
//        final String Amount_Pattern="^\\d{1,3}(\\.\\d{2})?$";
//        final String price="\\b\\d{1,3}(\\.\\d{2})?\\b";

        final String amount = "^\\d{1,4}(\\.\\d{2})$";

        Pattern pattern = Pattern.compile(amount);
        if (!val.isEmpty() && pattern.matcher(val).matches()) {
            return true;
        } else {
            return false;
        }
    }
    public boolean isValidAmount2(String val) {
//        final String Amount_Pattern="^\\d{1,3}(\\.\\d{2})?$";
//        final String price="\\b\\d{1,3}(\\.\\d{2})?\\b";

        DecimalFormat df=new DecimalFormat("0.00");
        float flt_val= Float.parseFloat(val);
        final String amount = "^\\d{1,4}(\\.\\d{2})$";

        Pattern pattern = Pattern.compile(amount);
        if (!val.isEmpty() && pattern.matcher(val).matches() && flt_val>=0.5) {
            return true;
        } else {
            return false;
        }
    }


    public boolean isMobileNumbervalid(String phoneNumber) {
        //^[0-9]{10}$
        //Pattern pattern = Pattern.compile("^\\+[0-9]{10,13}$");
        Pattern pattern = Pattern.compile("^[0-9]{10}$");
        Matcher matcher = pattern.matcher(phoneNumber.trim());
        //
        if (matcher.matches() && !phoneNumber.isEmpty())
            return true;
        else
            return false;
    }

    /***
     * @param context
     * @return Check whether internet connectivity is on or off.
     */
    public boolean isConnectivity(Context context) {
        //
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager.getActiveNetworkInfo() != null
                && connectivityManager.getActiveNetworkInfo().isAvailable()
                && connectivityManager.getActiveNetworkInfo().isConnected()) {
            return true;
        } else {
            return false;
        }
    }

    /***
     * Check only accepts AlphaBet Character.
     */
    public boolean isAlphabetCheck(String charCheck) {
        Pattern pattern = Pattern.compile("[a-zA-Z'-]+");
        Matcher matcher = pattern.matcher(charCheck);

        if (matcher.matches())
            return true;
        else
            return false;
    }

    //image convert in string
    public String bitmapToString(Bitmap bitmap) {

        String temp = null;
        try {
            ByteArrayOutputStream ByteStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, ByteStream);
            byte[] b = ByteStream.toByteArray();
            temp = Base64.encodeToString(b, Base64.NO_WRAP);

            return temp;
        } catch (OutOfMemoryError error) {
            error.printStackTrace();
            return temp;
        }

    }

    public static boolean currentVersionSupportBigNotification() {
        int sdkVersion = android.os.Build.VERSION.SDK_INT;
        if (sdkVersion >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            return true;
        }
        return false;
    }

    public static boolean currentVersionSupportLockScreenControls() {
        int sdkVersion = android.os.Build.VERSION.SDK_INT;
        if (sdkVersion >= android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            return true;
        }
        return false;
    }

    //string data convert in bitmapimage
    public Bitmap stringToBitmap(String imageData) {
        try {
            byte[] encodeByte = Base64.decode(imageData, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

   String response = null;

   public AlertDialog.Builder showDialogBox(Context context, String title, String msg) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(title);
        alertDialog.setMessage(msg);
        alertDialog.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to execute after dialog
                        response = "0";
                    }
                });
        alertDialog.show();
        return alertDialog;
    }

    public String stripHtml(String html) {
        return Html.fromHtml(html).toString();
    }

//    public String getJsonData(String... param) {
//        JSONObject mainObj = new JSONObject();
//        JSONObject marchantObj = new JSONObject();
//        JSONObject tansObj = new JSONObject();
//        JSONObject tranRequestObj = new JSONObject();
//        JSONObject paymentObj = new JSONObject();
//        JSONObject creditCardObj = new JSONObject();
//        JSONObject customerIdObj = new JSONObject();
//        JSONObject customerBillObj = new JSONObject();
//
//        try {
//            marchantObj.put("name", com.medbeds.Const.APP_LOGIN_ID);
//            marchantObj.put("transactionKey", "54e4yBcb35JdMb8F");
//            marchantObj.put("mobileDeviceId", "123456789");
//            tansObj.put("merchantAuthentication", (JSONObject) marchantObj);
//
//
//            //tansObj.put("refId", param[0]);
//            tranRequestObj.put("transactionType", com.medbeds.Const.PAYMENT_TRANSACTION_TYPE);
//            tranRequestObj.put("amount", param[1]);
//            tranRequestObj.put("poNumber", "");
//            //
//            customerIdObj.put("id", param[5]);
//            tranRequestObj.put("customer", (JSONObject) customerIdObj);
//            //
//            customerBillObj.put("firstName", param[6]);
//            customerBillObj.put("lastName", param[7]);
//            customerBillObj.put("company", param[8]);
//            customerBillObj.put("address", param[9]);
//            customerBillObj.put("city", param[10]);
//            customerBillObj.put("state", param[11]);
//            customerBillObj.put("zip", param[12]);
//            customerBillObj.put("country", param[13]);
//            tranRequestObj.put("billTo", (JSONObject) customerBillObj);
//            tranRequestObj.put("customerIP", param[14]);
//            //
//            creditCardObj.put("cardNumber", param[2]);
//            creditCardObj.put("expirationDate", param[3]);
//            creditCardObj.put("cardCode", param[4]);
//            paymentObj.put("creditCard", (JSONObject) creditCardObj);
//            //
//            tranRequestObj.put("payment", (JSONObject) paymentObj);
//            //
//            tansObj.put("transactionRequest", tranRequestObj);
//            mainObj.put("createTransactionRequest", (JSONObject) tansObj);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        //
//        return mainObj.toString();
//    }

    public String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (Exception ex) {
            Log.e("IP Address", ex.toString());
        }
        return null;
    }


    public static String getIPAddress(boolean useIPv4) {
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                for (InetAddress addr : addrs) {
                    if (!addr.isLoopbackAddress()) {
                        String sAddr = addr.getHostAddress();
                        //boolean isIPv4 = InetAddressUtils.isIPv4Address(sAddr);
                        boolean isIPv4 = sAddr.indexOf(':') < 0;

                        if (useIPv4) {
                            if (isIPv4)
                                return sAddr;
                        } else {
                            if (!isIPv4) {
                                int delim = sAddr.indexOf('%'); // drop ip6 zone suffix
                                return delim < 0 ? sAddr.toUpperCase() : sAddr.substring(0, delim).toUpperCase();
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
        } // for now eat exceptions
        return "";
    }

    public static void hideKeyboardIfOpen(Activity activity)
    {
        View view = activity.getCurrentFocus();
        if (view != null)
        {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

    }

    public void d(String message) {
        Log.i(TAG, message);
    }


//
//    private void member_info_multipart()
//    {
//
//
//        final String address = edt_member_reg_address.getText().toString().trim();
//        final String city = edt_member_reg_city.getText().toString().trim();
//        final String state = (String) spn_state.getSelectedItem();
//        final String zip = edt_member_reg_zip.getText().toString().trim();
//        new AsyncTask<Void, Void, String>() {
//
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//                progressDialog = new ProgressDialog(ActivityMemberInfo.this);
//                progressDialog.setMessage("Loading..");
//                progressDialog.setCanceledOnTouchOutside(false);
//                progressDialog.setIndeterminate(true);
//                progressDialog.setCancelable(true);
//                progressDialog.show();
//            }
//
//            @Override
//            protected String doInBackground(Void... params) {
//                JSONObject jsonObject = new JSONObject();
//                String result="1";
//
//                try {
//                    MultipartUtility multipart = new MultipartUtility(com.medbeds.Const.MEMBER_INFO);
//                    multipart.addFormField("user_id",Prefs.getPrefInstance().getValue(ActivityMemberInfo.this,com.medbeds.Const.USER_ID,""));
//                    multipart.addFormField("address",address);
//                    multipart.addFormField("city",city);
//                    multipart.addFormField("state",state);
//                    multipart.addFormField("zip", zip);
//                    multipart.addFilePart("uploadedfile", file_image);
//
//                    List<String> response = multipart.finish();
//                    Utils.getInstance().d("response" + response.toString());
//
//
//                    StringBuilder sb=new StringBuilder();
//                    for (String line : response) {
//                        Utils.getInstance().d("response" + line);
//
//                        sb.append(line);
//                        sb.append("\t");
//
//                    }
////                    result= Arrays.toString(response.toArray());
//                    result=sb.toString();
//                } catch (IOException ex) {
//                    System.err.println(ex);
//                }
//
//
//                Utils.getInstance().d("Member Info:: " + result);
//                return result;
//            }
//
//            @Override
//            protected void onPostExecute(String response) {
//                super.onPostExecute(response);
//                progressDialog.dismiss();
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//
//                    Utils.getInstance().d("json Object " + jsonObject.toString());
//
//                    if (jsonObject.getString("status").equals("1")) {
//
//
//
//                        android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(ActivityMemberInfo.this);
//                        alertDialog.setTitle("SUCCESS");
//                        alertDialog.setMessage(jsonObject.getString("msg"));
//                        alertDialog.setCancelable(false);
//
//                        alertDialog.setPositiveButton("OK",
//                                new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        // Write your code here to execute after dialog
//                                        Prefs.getPrefInstance().setValue(ActivityMemberInfo.this, com.medbeds.Const.STATUS, "2");
//                                        if (!imageToString.equals("1") && !imageToString.equals(null)) {
//                                            Prefs.getPrefInstance().setValue(ActivityMemberInfo.this, com.medbeds.Const.USERIMAGE, imageToString);
//                                        }
//                                        dialog.dismiss();
//                                        Intent intent = new Intent(ActivityMemberInfo.this, ActivityPracticeList.class);
//                                        startActivity(intent);
//                                        finish();
//
//                                    }
//                                });
//                        alertDialog.show();
//
//                    }
//                    if (jsonObject.getInt("status") == 0) {
//                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(ActivityMemberInfo.this);
//                        alertDialog.setTitle("Failed");
//                        alertDialog.setMessage(jsonObject.getString("msg"));
//
//                        alertDialog.setPositiveButton("OK",
//                                new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int which) {
////                                                // Write your code here to execute after dialog
//                                        dialog.dismiss();
//                                    }
//                                });
//                        alertDialog.show();
//
//                    }
//
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }.execute();
//
//    }


}
