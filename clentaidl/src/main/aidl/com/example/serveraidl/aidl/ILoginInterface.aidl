// ILoginInterface.aidl
package com.example.serveraidl.aidl;

// Declare any non-default types here with import statements

interface ILoginInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

     void login(String userName,String password);
}
