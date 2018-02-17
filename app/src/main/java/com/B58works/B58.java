package com.B58works;

/**
 * Created by Bharath.R(58) on 27-12-2017.
 */

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.Context;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.B58works.extra.*;
import com.whatsapp.aed;
import com.whatsapp.data.fk;
import com.whatsapp.Conversation;
import com.whatsapp.GroupChatInfo;
import com.whatsapp.HomeActivity;
import com.whatsapp.TextEmojiLabel;
import com.whatsapp.sn1;
import com.whatsapp.cj;
import com.whatsapp.contact.f;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;

public class B58 extends sn1 {
    public static boolean IsB58;
    public static Context ctx;
    public static View d;
    static Class n;
    public static SQLiteOpenHelper sql;

    static {
        com.B58works.B58.IsB58 = false;
        com.B58works.B58.n = B58.class;
    }

    public static boolean Archv_chats() {
        return getBoolean("Archv_chats");
    }

    public static boolean Audio_ears() {
        return getBoolean("Audio_ears");
    }

    public static boolean Audio_sensor() {
        return getBoolean("Audio_sensor");
    }

    public static boolean CallBHide() {
        return getBoolean("call_btn");
    }

    public static void ClearLogs(final Context context) {
        DeleteDirectory(new File(context.getFilesDir() + "/Logs"));
        Toast.makeText(com.B58works.B58.ctx, (CharSequence)"WhatsApp Logs have been cleared successfully.", Toast.LENGTH_SHORT).show();
    }

    public static void DeleteDirectory(final File file) {
        if (file.isDirectory()) {
            final File[] listFiles = file.listFiles();
            for (int i = 0; i < listFiles.length; ++i) {
                DeleteDirectory(listFiles[i]);
            }
        }
        file.delete();
    }

    public static int GetCounterId() {
        return com.B58works.B58.ctx.getResources().getIdentifier("counter", "id", com.B58works.B58.ctx.getPackageName());
    }

    public static LinkedHashMap<String, Integer> GetGroupMsgs(final String s) {
        final LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<String, Integer>();
        final Cursor rawQuery = com.B58works.B58.sql.getReadableDatabase().rawQuery("SELECT remote_resource, count(remote_resource) as total FROM messages WHERE key_remote_jid=\"" + s + "\" AND remote_resource!=\"\" GROUP BY remote_resource UNION SELECT remote_resource, count(key_from_me) as total FROM messages WHERE key_remote_jid=\"" + s + "\" AND key_from_me=1 And receipt_server_timestamp!=-1 GROUP BY remote_resource ORDER BY total DESC", (String[])null);
        rawQuery.moveToFirst();
        if (rawQuery != null && rawQuery.getCount() > 0) {
            do {
                linkedHashMap.put(rawQuery.getString(0), rawQuery.getInt(1));
            } while (rawQuery.moveToNext());
        }
        rawQuery.close();
        return linkedHashMap;
    }

    public static int GetNIcon(final Context context) {
        return Integer.parseInt(B58.ctx.getSharedPreferences("B58", 0).getString("notifybar_colors_list", "13"));
    }

    public static int Getchangelog() {
        return B58.ctx.getResources().getIdentifier("B58", "raw", B58.ctx.getPackageName());
    }

    public static int Getcheck_en_changelog() {
        return B58.ctx.getResources().getIdentifier("check_en_changelog", "string", B58.ctx.getPackageName());
    }

    public static int Getremind_me_later() {
        return B58.ctx.getResources().getIdentifier("remind_me_later", "string", B58.ctx.getPackageName());
    }

    public static int Getupgrade() {
        return B58.ctx.getResources().getIdentifier("upgrade", "string", B58.ctx.getPackageName());
    }

    public static void HidePicBar(final Conversation conversation) {
        if (getBoolean("B58pic")) {
            final FrameLayout frameLayout = conversation.findViewById(sn1.zconversation_contact_photo_frame());
            if (frameLayout != null) {
                frameLayout.setVisibility(View.GONE);
            }
        }
    }

    public static int LockString() {
        int n;
        if (getBoolean(jid() + "_locked")) {
            n = getResID("unlock", "string");
        }
        else {
            n = getResID("lock", "string");
        }
        return n;
    }

    public static String MaMy_Name2() {
        return B58.ctx.getSharedPreferences("com.whatsapp_preferences", 0).getString("push_name", "");
    }

    public static String MaMy_Number() {
        return B58.ctx.getSharedPreferences("com.whatsapp_preferences", 0).getString("registration_jid", "");
    }

    public static String MaMy_status() {
        return B58.ctx.getSharedPreferences("com.whatsapp_preferences", 0).getString("my_current_status", "");
    }

    static Intent OpenChat(final String s) {
        try {
            return Conversation.a(B58.ctx, s);
        }
        catch (Exception ex) {
            return null;
        }
    }

    private static void PrefSet() {
        SetPrefBool("usync_sidelist", true);
        SetPrefBool("conversation_delete_files", true);
        SetPrefBool("p2p_pay", true);
        SetPrefInt("document_limit_mb", 1024);
        SetPrefInt("group_description_length", 2048);
        SetPrefBool("optimistic_image_processing_enabled", true);
        SetPrefBool("restrict_groups", true);
        SetPrefBool("search_in_storage_usage", true);
        SetPrefBool("groups_v3", true);
        SetPrefBool("change_num_v2", true);
        SetPrefBool("mozjpeg", true);
        SetPrefBool("mms_resume_check_chatd", true);
        SetPrefBool("optimistic_image_processing_enabled", true);
    }

    public static void SetDB(final SQLiteOpenHelper sql) {
        com.B58works.B58.sql = sql;
    }

    public static void SetGroupChat(final boolean b) {
        SetPrefBool("is_group_chat", b);
    }

    public static boolean SetLock() {
        return getBoolean("Locked");
    }

    public static void SetMsgs(String s, final GroupChatInfo groupChatInfo, final View view) {
        final LinkedHashMap<String, Integer> counter = groupChatInfo.Counter;
        final TextView textView = (TextView)view.findViewById(GetCounterId());
        if (counter != null) {
            textView.setVisibility(View.VISIBLE);
            if (s.equals("me")) {
                s = null;
            }
            if (groupChatInfo.Counter.get(s) == null) {
                textView.setText((CharSequence)"0");
            }
            else {
                textView.setText((CharSequence)new StringBuilder().append(groupChatInfo.Counter.get(s)).toString());
            }
        }
    }

    public static void SetMsgs(String s, final GroupChatInfo groupChatInfo, final TextView textView) {
        if (groupChatInfo.Counter != null) {
            textView.setVisibility(View.VISIBLE);
            if (s.equals("me")) {
                s = null;
            }
            if (groupChatInfo.Counter.get(s) == null) {
                textView.setText((CharSequence)"0");
            }
            else {
                textView.setText((CharSequence)new StringBuilder().append(groupChatInfo.Counter.get(s)).toString());
            }
        }
    }

    private static void SetPrefBool(final String s, final boolean b) {
        final SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(com.B58works.B58.ctx).edit();
        edit.putBoolean(s, b);
        edit.apply();
    }

    private static void SetPrefBool1(final String s, final boolean b) {
        final SharedPreferences.Editor edit = com.B58works.B58.ctx.getSharedPreferences("B58", 0).edit();
        edit.putBoolean(s, b);
        edit.apply();
    }

    private static void SetPrefInt(final String s, final int n) {
        final SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(com.B58works.B58.ctx).edit();
        edit.putInt(s, n);
        edit.apply();
    }

    private static void SetPrefString(final String s, final String s2) {
        final SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(com.B58works.B58.ctx).edit();
        edit.putString(s, s2);
        edit.apply();
    }

    public static void SetShared(final PreferenceManager preferenceManager) {
        preferenceManager.setSharedPreferencesName("B58");
        preferenceManager.setSharedPreferencesMode(1);
    }

    public static void SetStatusChat(Conversation conversation, fk f) {
        while (true) {
            TextView textView = null;
            Label_0113: {
                try {
                    textView = conversation.findViewById(getResID("conversation_contact_global_status", "id"));
                    if (wacontactfinder(f)) {
                        SetGroupChat(true);
                        textView.setVisibility(View.GONE);
                    }
                    else {
                        SetGroupChat(false);
                        textView.setText(com.whatsapp.emoji.c.a((CharSequence)com.whatsapp.data.fk.t, (Context)conversation, (Paint)textView.getPaint()));
                        textView.setTextColor(sn1.getColor("ModChatGStatusT", -1));
                        textView.setSelected(true);
                        SetGroupChat(false);
                        textView.setBackgroundColor(sn1.getColor("ModChatGStatusB", Color.parseColor("#44000000")));
                        if (!statuschat()) {
                            break Label_0113;
                        }
                        textView.setVisibility(View.GONE);
                    }
                    return;
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    return;
                }
            }
            textView.setVisibility(View.VISIBLE);
            textView.setOnClickListener((View.OnClickListener)new com.B58works.extra.aa(textView));
        }
    }

    public static void ShowName(final android.support.v7.app.a a, Context ctx) {
        if (getBoolean("show_my_name_check")) {
            a.a(MaMy_Name2());
            if (getBoolean("subhead")) {
                if (getBoolean("number")) {
                    a.b(MaMy_Number());
                }
                else {
                    a.b(MaMy_status());
                }
            }
        }
    }

    public static void TxtSelect(final TextEmojiLabel textEmojiLabel) {
        if (getBoolean("TxtSelect")) {
            textEmojiLabel.setTextIsSelectable(true);
        }
    }

    public static boolean Up_size_limit() {
        return getBoolean("Up_size_limit");
    }

    public static Class a() {
        Serializable s;
        if (!getBoolean("Locked")) {
            s = HomeActivity.class;
        }
        else if (getBoolean("pt_enabled")) {
            s = pattern.class;
        }
        else {
            s = Locks.class;
        }
        return (Class)s;
    }

    private static void a(final String s) {
        ((ClipboardManager)getCtx().getSystemService(Context.CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText((CharSequence)"text", (CharSequence)s));
        Toast.makeText(getCtx(), (CharSequence)getString("B58Copy"), Toast.LENGTH_SHORT).show();
    }

    public static void a(final String s, final View view) {
        a(s);
    }

    public static int actionbar() {
        try {
            int n;
            if (getBool(B58.ctx, "actionbar")) {
                n = sn1.zwamod_style_stockcentered_conversation_actionbar();
            }
            else {
                n = sn1.zconversation_actionbar();
            }
            return n;
        }
        catch (Exception ex) {
            return 0;
        }
    }

    public static void addMenu(final HomeActivity homeActivity, final MenuItem menuItem) {
        if (menuItem.getItemId() == getResID("B58mods", "id")) {
            homeActivity.startActivity(new Intent((Context)homeActivity, (Class)Settings.class));
        }
        if (menuItem.getItemId() == getResID("restart", "id")) {
            rebootApp((Context)homeActivity);
        }
        if(menuItem.getItemId() == getResID("openchat", "id")){
            AlertDialog.Builder builder;
            builder = new AlertDialog.Builder(homeActivity);
            builder.setTitle("New Chat");
            builder.setMessage("Enter Number");
            final EditText input = new EditText(homeActivity);
            input.setHint("Ex: 91 1234512345");
            builder.setView(input);
            builder.setPositiveButton("Message!",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            String number = input.getText().toString().trim().replace("+", "").replace(" ", "");
                            String s = number + "@s.whatsapp.net";
                            if (number.contains("-")) {
                                s = number + "@g.us";
                            }
                            homeActivity.startActivity(com.B58works.B58.OpenChat(s));
                        }
                    });
            builder.setNegativeButton("Cancel",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            builder.show();
        }
    }

    public static void b581(final DialogInterface dialogInterface, final int n) {
        dialogInterface.dismiss();
    }

    public static void b582(final fk fk, final Activity activity, final boolean b, final DialogInterface dialogInterface, final int n) {
        cj.a().a(fk, activity, Integer.valueOf(8), b, true);
    }

    public static void b583(final fk fk, final Activity activity, final boolean b, final DialogInterface dialogInterface, final int n) {
        switch (n) {
            case 0: {
                cj.a().a(fk, activity, Integer.valueOf(8), b, false);
                break;
            }
            case 1: {
                b584(activity);
                break;
            }
        }
    }

    public static void b584(final Activity activity) {
        try {
            activity.startActivity(new Intent("android.intent.action.DIAL", Uri.parse("tel:+" + f.b(Privacy.JID))));
        }
        catch (ActivityNotFoundException ex) {
            Toast.makeText(B58.ctx, (CharSequence)"No dialer app found. Please check.", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("ResourceType")
    public static void callDlg(final fk fk, final Activity activity, final boolean b) {
        final String string = getString("audio_call");
        final AlertDialog.Builder alertDialog$Builder = new AlertDialog.Builder((Context)activity);
        if (b) {
            alertDialog$Builder.setMessage((CharSequence)getString("video_call_confirmation_text")).setNegativeButton(17039360, al.a).setPositiveButton((CharSequence)getString("call"), (DialogInterface.OnClickListener)new am(fk, activity, b));
        }
        else {
            alertDialog$Builder.setItems((CharSequence[])new String[] { string, "Call over SIM" }, (DialogInterface.OnClickListener)new an(fk, activity, b));
        }
        alertDialog$Builder.create().show();
    }

    public static void clickcopytext(final TextView textView) {
        textView.setOnClickListener((View.OnClickListener)new com.B58works.extra.ak(textView.getText().toString()));
    }

    public static boolean contact_online_toast() {
        return getBoolean("contact_online_toast");
    }

    public static String contactstringsfinder(final fk fk) {
        return com.whatsapp.fj.a().b(fk);
    }

    public static void copyFile(final String s, final String s2) throws IOException {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        Label_0086: {
            try {
                final File file = new File(s);
                final File file2 = new File(s2);
                fileInputStream = new FileInputStream(file);
                fileOutputStream = new FileOutputStream(file2);
                final byte[] array = new byte[1024];
                while (true) {
                    final int read = fileInputStream.read(array);
                    if (read <= 0) {
                        break Label_0086;
                    }
                    fileOutputStream.write(array, 0, read);
                }
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            return;
        }
        fileInputStream.close();
        fileOutputStream.close();
    }

    public static void copyMyText(final TextView textView) {
        ((ClipboardManager)textView.getContext().getSystemService(Context.CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText((CharSequence)"text", textView.getText()));
        Toast.makeText(textView.getContext(), (CharSequence)getString("B58Copy"), Toast.LENGTH_SHORT).show();
    }

    public static boolean getBool(final Context context, final String s) {
        return context.getSharedPreferences("B58", 0).getBoolean(s, false);
    }

    public static boolean getBoolean(final String s) {
        return com.B58works.B58.ctx.getSharedPreferences("B58", 0).getBoolean(s, false);
    }

    public static boolean getBoolean2(final String s) {
        return com.B58works.B58.ctx.getSharedPreferences("whatsapp_preferences", 0).getBoolean(s, false);
    }

    public static int getContactStatusStr() {
        return getResID("conversations_contact_status1", "id");
    }

    public static Context getCtx() {
        return com.B58works.B58.ctx;
    }

    public static boolean getHideInfo() {
        return com.B58works.B58.ctx.getSharedPreferences("B58", 0).getBoolean("B58_hideinfo", false);
    }

    public static int getID(final String s, final String s2) {
        int n = getfreqidq(s);
        if (n == -1) {
            n = com.B58works.B58.ctx.getResources().getIdentifier(s, s2, com.B58works.B58.ctx.getPackageName());
        }
        return n;
    }

    public static int getNIcon() {
        return com.B58works.B58.ctx.getResources().getIdentifier("n_" + GetNIcon(com.B58works.B58.ctx), "drawable", com.B58works.B58.ctx.getPackageName());
    }

    public static int getPrefInt(final String s) {
        return com.B58works.B58.ctx.getSharedPreferences("B58", 0).getInt(s, 0);
    }

    public static int getPrefInt2(final String s) {
        return com.B58works.B58.ctx.getSharedPreferences("whatsapp_preferences", 0).getInt(s, 0);
    }

    public static int getResID(final String s, final String s2) {
        return com.B58works.B58.ctx.getResources().getIdentifier(s, s2, com.B58works.B58.ctx.getPackageName());
    }

    public static String getString(final String s) {
        return com.B58works.B58.ctx.getString(getResID(s, "string"));
    }

    public static int getfreqidq(final String s) {
        int int1 = -1;
        try {
            final Field declaredField = com.B58works.B58.n.getDeclaredField(s.toLowerCase(Locale.ENGLISH));
            int1 = declaredField.getInt(declaredField);
            return int1;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return int1;
        }
    }

    public static void init(final Context sctx) {
        B58$5.b = new HashMap();
        if (sctx instanceof Activity) {
            com.B58works.B58.ctx = sctx.getApplicationContext();
            Privacy.pctx = sctx.getApplicationContext();
            Settings.sctx = sctx.getApplicationContext();
        }
        else {
            com.B58works.B58.ctx = sctx;
            Privacy.pctx = sctx;
            Settings.sctx = sctx;
        }
        if (com.B58works.B58.ctx == null || Privacy.pctx == null || Settings.sctx == null) {
            Log.d("B58Mods", "Context var initialized to NULL!!!");
        }
        PrefSet();
        tgchooser();
    }

    public static void initB58(final TextView textView) {
        if (textView.getPaddingRight() > 8 && textView.getId() == zstatus()) {
            textView.setOnClickListener((View.OnClickListener)new aa(textView));
        }
    }

    public static Intent isLocked(final Intent intent) {
        final String stringExtra = intent.getStringExtra("jid");
        final String substring = stringExtra.substring(0, stringExtra.indexOf("@"));
        Intent intent2 = intent;
        if (getBoolean(substring + "_locked")) {
            if (getBoolean("pt_enabled")) {
                intent2 = new Intent(B58.ctx, (Class)pattern.class).putExtra("jid", substring).putExtra("intent", (Parcelable)intent);
            }
            else {
                intent2 = new Intent(B58.ctx, (Class)Locks.class).putExtra("jid", substring).putExtra("intent", (Parcelable)intent);
            }
        }
        return intent2;
    }

    public static boolean isNetworkAvailable() {
        try {
            final ConnectivityManager connectivityManager = (ConnectivityManager)getCtx().getSystemService(Context.CONNECTIVITY_SERVICE);
            return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
        }
        catch (Exception ex) {
            return false;
        }
    }

    public static boolean isOnline(final Context context) {
        return ((ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
    }

    public static String jid() {
        return B58.ctx.getSharedPreferences("B58", 0).getString("jid", "");
    }

    public static Class m1() {
        Serializable s;
        if (!getBoolean("Locked")) {
            s = Conversation.class;
        }
        else {
            s = Locks.class;
        }
        return (Class)s;
    }

    public static Intent notifIn(Intent putExtra) {
        if (getBoolean("Locked")) {
            putExtra = new Intent(getCtx(), a()).putExtra("intent", (Parcelable)putExtra);
        }
        return putExtra;
    }

    public static int oldui() {
        int n;
        if (getBoolean("oldui")) {
            n = getResID("homeo", "layout");
        }
        else {
            n = getResID("home", "layout");
        }
        return n;
    }

    public static boolean onlinechat() {
        return getBoolean("onlinechat");
    }

    public static void presencemanagerfinder(final fk fk) {
        aed.a().a(fk);
    }

    public static void putInt(final String s, final int n) {
        B58.ctx.getSharedPreferences("B58", 0).edit().putInt(s, n).commit();
    }

    public static void putString(final String s, final String s2) {
        B58.ctx.getSharedPreferences("B58", 0).edit().putString(s, s2).commit();
    }

    public static void rebootApp(final Context context) {
        ((AlarmManager)context.getSystemService(Context.ALARM_SERVICE)).set(AlarmManager.RTC, 100L + System.currentTimeMillis(), PendingIntent.getActivity(context, 123456, context.getPackageManager().getLaunchIntentForPackage(context.getPackageName()), PendingIntent.FLAG_CANCEL_CURRENT));
        System.exit(0);
    }

    public static void setLock(final Activity activity) {
        final String jid = jid();
        if (getBoolean(jid + "_locked")) {
            B58.ctx.getSharedPreferences("B58", 0).edit().putBoolean(jid + "_locked", false).apply();
        }
        else if (getBoolean("pt_enabled")) {
            activity.startActivity(new Intent((Context)activity, (Class)patternC.class).putExtra("jid", jid));
        }
        else {
            activity.startActivity(new Intent((Context)activity, (Class)LocksC.class).putExtra("jid", jid));
        }
    }

    public static MenuItem setMenuC(final Menu menu) {
        return menu.add(1, getResID("openc", "id"), 0, getResID("opench", "string"));
    }

    public static MenuItem setMenuR(final Menu menu) {
        return menu.add(1, getResID("restart", "id"), 0, getResID("B58Restart", "string"));
    }

    public static MenuItem setMenuS(final Menu menu) {
        return menu.add(1, getResID("B58mods", "id"), 0, getResID("B58Mods", "string"));
    }

    public static void setStatusText(final fk fk, final TextView textView) {
        try {
            if (Privacy.HideSeen() || wacontactfinder(fk) || onlinechat() || (!isNetworkAvailable())) {
                textView.setVisibility(View.GONE);
                return;
            }
            presencemanagerfinder(fk);
            final String replace = contactstringsfinder(fk).replace("last seen", "");
            if (replace.contains("online")) {
                textView.setTextColor(sn1.getColor("ModOnlineColor"));
                textView.setTypeface(textView.getTypeface(), 1);
            }
            else {
                textView.setTextColor(sn1.getColor("ModlastseenColor"));
                textView.setTypeface(textView.getTypeface(), 0);
            }
            textView.setText((CharSequence)replace);
            textView.setVisibility(View.VISIBLE);
        }
        catch (Exception ignored) {}
    }

    public static void status(final TextView textView, final View view) {
        copyMyText(textView);
    }

    public static boolean statuschat() {
        return getBoolean("statuschat");
    }

    public static String stripJID(String substring) {
        if (substring.contains("@g.us") || substring.contains("@s.whatsapp.net") || substring.contains("@broadcast")) {
            substring = substring.substring(0, substring.indexOf("@"));
        }
        return substring;
    }

    private static void tgchooser() {
        int n = 1;
        final Context ctx = com.B58works.B58.ctx;
        if (!getBoolean("tgchoose")) {
            n = 0;
        }
        SetPrefInt("gif_provider", n);
    }

    public static boolean wacontactfinder(final fk fk) {
        return fk.a();
    }
}
