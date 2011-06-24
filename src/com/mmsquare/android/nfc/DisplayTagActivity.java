package com.mmsquare.android.nfc;

import android.app.Activity;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.TextView;
import org.w3c.dom.Text;

public class DisplayTagActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tag);
         resolveIntent(getIntent());
    }

    private void resolveIntent(Intent intent) {
        Tag t = getNdefTag(intent);

        String[] tech = t.getTechList();
        TextView mLabel = (TextView) findViewById(R.id_tag.text_technology);
        TextView idLabel = (TextView) findViewById(R.id_tag.text_id);

        StringBuilder sb = new StringBuilder();

        for (String s:tech) {
            sb.append(s);
            sb.append("\n");
        }

        mLabel.setText(sb.toString());
        idLabel.setText(t.toString());

    }

    Tag getNdefTag(Intent intent) {

        String action = intent.getAction();
        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)) {

            Tag tag = (Tag) intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            return tag;
        }
        return null;
    }

    @Override
    public void onNewIntent(Intent intent) {
      setIntent(intent);
      resolveIntent(intent);
    }

}
