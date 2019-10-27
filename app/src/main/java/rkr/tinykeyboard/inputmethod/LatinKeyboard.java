/*
 * Copyright (C) 2008-2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package rkr.tinykeyboard.inputmethod;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.inputmethodservice.Keyboard;
import android.view.inputmethod.EditorInfo;

public class LatinKeyboard extends Keyboard {

    private static final int KEYCODE_LANGUAGE_SWITCH = -101;

    private Key mEnterKey;
    private Key mModeChangeKey;
    private Key mLanguageSwitchKey;
    private Key mSavedModeChangeKey;
    private Key mSavedLanguageSwitchKey;
    
    public LatinKeyboard(Context context, int xmlLayoutResId) {
        super(context, xmlLayoutResId);
    }

    @Override
    protected Key createKeyFromXml(Resources res, Row parent, int x, int y, 
            XmlResourceParser parser) {
        Key key = new Key(res, parent, x, y, parser);
        if (key.codes[0] == 10) {
            mEnterKey = key;
        } else if (key.codes[0] == Keyboard.KEYCODE_MODE_CHANGE) {
            mModeChangeKey = key;
            mSavedModeChangeKey = new Key(res, parent, x, y, parser);
        } else if (key.codes[0] == KEYCODE_LANGUAGE_SWITCH) {
            mLanguageSwitchKey = key;
            mSavedLanguageSwitchKey = new Key(res, parent, x, y, parser);
        }
        return key;
    }

    void setLanguageSwitchKeyVisibility(boolean visible) {
        if (visible) {
            // The language switch key should be visible. Restore the size of the mode change key
            // and language switch key using the saved layout.
            mModeChangeKey.width = mSavedModeChangeKey.width;
            mModeChangeKey.x = mSavedModeChangeKey.x;
            mLanguageSwitchKey.width = mSavedLanguageSwitchKey.width;
            mLanguageSwitchKey.icon = mSavedLanguageSwitchKey.icon;
            mLanguageSwitchKey.iconPreview = mSavedLanguageSwitchKey.iconPreview;
        } else {
            // The language switch key should be hidden. Change the width of the mode change key
            // to fill the space of the language key so that the user will not see any strange gap.
            mModeChangeKey.width = mSavedModeChangeKey.width + mSavedLanguageSwitchKey.width;
            mLanguageSwitchKey.width = 0;
            mLanguageSwitchKey.icon = null;
            mLanguageSwitchKey.iconPreview = null;
        }
    }

    /**
     * This looks at the ime options given by the current editor, to set the
     * appropriate label on the keyboard's enter key (if it has one).
     */
    void setImeOptions(Resources res, int options) {
        if (mEnterKey == null) {
            return;
        }

        switch (options&(EditorInfo.IME_MASK_ACTION|EditorInfo.IME_FLAG_NO_ENTER_ACTION)) {
            case EditorInfo.IME_ACTION_GO:
                mEnterKey.iconPreview = null;
                mEnterKey.icon = null;
                mEnterKey.label = res.getText(R.string.label_go_key);
                break;
            case EditorInfo.IME_ACTION_NEXT:
                mEnterKey.iconPreview = null;
                mEnterKey.icon = null;
                mEnterKey.label = res.getText(R.string.label_next_key);
                break;
            case EditorInfo.IME_ACTION_SEARCH:
                mEnterKey.icon = null;
                mEnterKey.label = res.getString(R.string.search);
                break;
            case EditorInfo.IME_ACTION_SEND:
                mEnterKey.iconPreview = null;
                mEnterKey.icon = null;
                mEnterKey.label = res.getText(R.string.label_send_key);
                break;
            default:
                mEnterKey.icon = null;
                mEnterKey.label = res.getString(R.string.enter);
                break;
        }
    }
}
