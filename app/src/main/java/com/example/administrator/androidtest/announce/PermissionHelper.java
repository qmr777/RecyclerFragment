package com.example.administrator.androidtest.announce;

import android.app.Activity;
import android.os.Build;
import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by qmr on 2016/9/12.
 */
public class PermissionHelper {

    public static final String TAG = "PermissionHelper";

    private static final Class<RequestPermission> clazz = RequestPermission.class;

    public static Object req(final Activity activity){
        Log.i(TAG, "req: ");
        Object o = null;
        Method[] methods = activity.getClass().getDeclaredMethods();
        for(Method method : methods){
            if(method.isAnnotationPresent(clazz)){//
                final String[] v = method.getAnnotation(clazz).value();
                Log.i(TAG, "req: " + method.getName());
                o = Proxy.newProxyInstance(activity.getClassLoader(), new Class<?>[]{ReqPermission.class}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            Log.i(TAG, "invoke: request");
                            activity.requestPermissions(v,0);
                        }
                        return method.invoke(activity);
                    }
                });
            }
        }
        return o;
    }
}
