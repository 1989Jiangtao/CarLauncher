package com.cjt.carlauncher.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.util.Log;

import java.util.List;

import es.dmoral.toasty.Toasty;

/**
 * @PackageName com.cjt.carlauncher.util
 * @ProjectName CarLauncher
 * @Author CaoJiangtao
 * @Date on 2017/11/14 14:30
 * @Email cjt2014@qq.com
 * @Describe 根据图标启动APP
 * @Version V-1.0.0
 */

public class AppUtil {
    /**
     * 通过指定的包名启动应用
     *
     * @param context     上下文
     * @param packageName 指定启动的包名
     */
    public static void openAppByPackageName(Context context, String packageName) {
        if (checkApplication(context, packageName)) {
            Intent localIntent = new Intent("android.intent.action.MAIN", null);
            localIntent.addCategory("android.intent.category.LAUNCHER");
            List<ResolveInfo> appList = context.getPackageManager().queryIntentActivities(localIntent, 0);
            for (int i = 0; i < appList.size(); i++) {

                ResolveInfo resolveInfo = appList.get(i);
                String packageStr = resolveInfo.activityInfo.packageName;
                String className = resolveInfo.activityInfo.name;

                if (packageStr.equals(packageName)) {
                    // 这个就是你想要的那个Activity
                    ComponentName cn = new ComponentName(packageStr, className);
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setComponent(cn);
                    context.startActivity(intent);
                    Log.d("CJT", "openApp-----111---打开完成！！");
                }
            }
        } else {
            Toasty.warning(context, "未安装此应用").show();
        }
    }

    /**
     * 卸载指定应用的包名
     *
     * @param context     上下文
     * @param packageName 指定的应用包名
     */
    public static void unInstall(Context context, String packageName) {
        if (checkApplication(context, packageName)) {
            Uri packageURI = Uri.parse("package:" + packageName);
            Intent intent = new Intent(Intent.ACTION_DELETE);
            intent.setData(packageURI);
            context.startActivity(intent);
        }
    }

    /**
     * 判断该包名的应用是否安装
     *
     * @param context     上下文
     * @param packageName 应用包名
     * @return 是否安装
     */
    public static boolean checkApplication(Context context, String packageName) {
        if (packageName == null || "".equals(packageName)) {
            return false;
        }
        try {
            context.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

}
