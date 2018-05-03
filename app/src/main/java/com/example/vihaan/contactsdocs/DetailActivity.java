package com.example.vihaan.contactsdocs;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    public static final String TAG = DetailActivity.class.getSimpleName();
    public static final String KEY_LOOKUP_KEY = "look_up_key";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mLookupKey = getIntent().getStringExtra(KEY_LOOKUP_KEY);
        getSupportLoaderManager().initLoader(DETAILS_QUERY_ID, null, this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    private static final String[] PROJECTION =
            {
                    ContactsContract.Contacts.Data._ID,
                    ContactsContract.Contacts.Data.MIMETYPE,
                    ContactsContract.Data.DATA1,
                    ContactsContract.Contacts.Data.DATA2,
                    ContactsContract.Contacts.Data.DATA3,
                    ContactsContract.Contacts.Data.DATA4,
                    ContactsContract.Contacts.Data.DATA5,
                    ContactsContract.Contacts.Data.DATA6,
                    ContactsContract.Contacts.Data.DATA7,
                    ContactsContract.Contacts.Data.DATA8,
                    ContactsContract.Contacts.Data.DATA9,
                    ContactsContract.Contacts.Data.DATA10,
                    ContactsContract.Contacts.Data.DATA11,
                    ContactsContract.Contacts.Data.DATA12,
                    ContactsContract.Contacts.Data.DATA13,
                    ContactsContract.Contacts.Data.DATA14,
                    ContactsContract.Contacts.Data.DATA15
            };

    // Defines the selection clause
    private static final String SELECTION = ContactsContract.Data.LOOKUP_KEY + " = ?";
    // Defines the array to hold the search criteria
    private String[] mSelectionArgs = {""};
    /*
     * Defines a variable to contain the selection value. Once you
     * have the Cursor from the Contacts table, and you've selected
     * the desired row, move the row's LOOKUP_KEY value into this
     * variable.
     */
    private String mLookupKey;
    private static final String SORT_ORDER = ContactsContract.Data.MIMETYPE;

    int DETAILS_QUERY_ID = 0;


    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        // Assigns the selection parameter
        mSelectionArgs[0] = mLookupKey;
        // Starts the query
        CursorLoader mLoader =
                new CursorLoader(
                        this,
                        ContactsContract.Data.CONTENT_URI,
                        null,
                        SELECTION,
                        mSelectionArgs,
                        SORT_ORDER
                );
        return mLoader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        Log.d(TAG, data.getColumnCount() +"");
        String vaules = DatabaseUtils.dumpCursorToString(data);
        Log.d(TAG, "cursor: " + vaules);
        TextView detailTV = findViewById(R.id.detailTV);
        detailTV.setText(vaules);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }
}
