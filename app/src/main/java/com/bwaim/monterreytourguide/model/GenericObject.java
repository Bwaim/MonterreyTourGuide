/*
 *    Copyright 2018 Fabien Boismoreau
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.bwaim.monterreytourguide.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Fabien Boismoreau on 14/04/2018.
 * <p>
 */

public class GenericObject implements Serializable, Parcelable {

    /**
     * image resource id
     */
    private int imageResId;

    /**
     * name resource id
     */
    private int nameResId;

    /**
     * summary resource id
     */
    private int summaryResId;

    public static final Creator<GenericObject> CREATOR = new Creator<GenericObject>() {
        @Override
        public GenericObject createFromParcel(Parcel source) {
            return new GenericObject(source);
        }

        @Override
        public GenericObject[] newArray(int size) {
            return new GenericObject[size];
        }
    };

    public GenericObject(int imageResId, int nameResId, int summaryResId) {
        this.imageResId = imageResId;
        this.nameResId = nameResId;
        this.summaryResId = summaryResId;
    }

    private GenericObject(Parcel in) {
        imageResId = in.readInt();
        nameResId = in.readInt();
        summaryResId = in.readInt();
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public int getNameResId() {
        return nameResId;
    }

    public void setNameResId(int nameResId) {
        this.nameResId = nameResId;
    }

    public int getSummaryResId() {
        return summaryResId;
    }

    public void setSummaryResId(int summaryResId) {
        this.summaryResId = summaryResId;
    }

    /**
     * Describe the kinds of special objects contained in this Parcelable
     * instance's marshaled representation. For example, if the object will
     * include a file descriptor in the output of {@link #writeToParcel(Parcel, int)},
     * the return value of this method must include the
     * {@link #CONTENTS_FILE_DESCRIPTOR} bit.
     *
     * @return a bitmask indicating the set of special object types marshaled
     * by this Parcelable object instance.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imageResId);
        dest.writeInt(nameResId);
        dest.writeInt(summaryResId);
    }
}
