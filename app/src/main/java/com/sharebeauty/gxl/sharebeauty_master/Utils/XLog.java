package com.sharebeauty.gxl.sharebeauty_master.Utils;

import android.util.Log;

/**
 * 
 * 1.Provide functions same as {@link Log} but check whether the
 * build is debugable before.</br>
 * 
 * 2.Provide functions with default tag(simple class name): </br>
 * {@link #d(String)}, {@link #d(String, Throwable)}, {@link #e(String)},
 * {@link #e(String, Throwable)}, {@link #i(String)},
 * {@link #i(String, Throwable)}, {@link #println(int, String)},
 * {@link #v(String)}, {@link #v(String, Throwable)}, {@link #w(String)},
 * {@link #wMsg(String, Throwable)}, {@link #wtf(String)},
 * {@link #wtfMsg(String, Throwable)}.</br></br>
 * 
 * @author as
 */

@SuppressWarnings("all")
public class XLog {

	private XLog() {
	}

	/**
	 * 在Release 版本的软件上将 DEBUG 置为 false 即可关闭 Log 输出了
	 */
	public static boolean DEBUG = true;

	/*
	 * the inner wrap information
	 */
	private static String[] wrapInfo(String msg) {
		Throwable t = new Throwable();
		StackTraceElement e = t.getStackTrace()[2];
		return new String[] { e.getClassName(),
				"[" + e.getMethodName() + ":" + e.getLineNumber() + "]" + msg };
	}

	/**
	 * @see Log#d(String, String)
	 */
	public static int d(String tag, String msg) {
		return DEBUG ? Log.d(tag, msg) : 0;
	}

	/**
	 * @see Log#d(String, String)
	 */
	public static int d(String msg) {
		if (DEBUG) {
			String[] wrapInfo = wrapInfo(msg);
			return Log.d(wrapInfo[0], wrapInfo[1]);
		}
		return 0;
	}

	/**
	 * @see Log#e(String, String)
	 */
	public static int e(String tag, String msg) {
		return DEBUG ? Log.e(tag, msg) : 0;
	}

	/**
	 * @see Log#e(String, String)
	 */
	public static int e(String msg) {
		if (DEBUG) {
			String[] wrapInfo = wrapInfo(msg);
			return Log.e(wrapInfo[0], wrapInfo[1]);
		}
		return 0;
	}

	/**
	 * @see Log#i(String, String)
	 */
	public static int i(String tag, String msg) {
		return DEBUG ? Log.i(tag, msg) : 0;
	}

	/**
	 * @see Log#i(String, String)
	 */
	public static int i(String msg) {
		if (DEBUG) {
			String[] wrapInfo = wrapInfo(msg);
			return Log.i(wrapInfo[0], wrapInfo[1]);
		}
		return 0;
	}

	/**
	 * @see Log#v(String, String)
	 */
	public static int v(String tag, String msg) {
		return DEBUG ? Log.v(tag, msg) : 0;
	}

	/**
	 * @see Log#v(String, String)
	 */
	public static int v(String msg) {
		if (DEBUG) {
			String[] wrapInfo = wrapInfo(msg);
			return Log.v(wrapInfo[0], wrapInfo[1]);
		}
		return 0;
	}

	/**
	 * @see Log#w(String, String)
	 */
	public static int w(String tag, String msg) {
		return DEBUG ? Log.w(tag, msg) : 0;
	}

	/**
	 * @see Log#w(String, String)
	 */
	public static int w(String msg) {
		if (DEBUG) {
			String[] wrapInfo = wrapInfo(msg);
			return Log.w(wrapInfo[0], wrapInfo[1]);
		}
		return 0;
	}

	/**
	 * @see Log#wtf(String, String)
	 */
	public static int wtf(String tag, String msg) {
		return DEBUG ? Log.wtf(tag, msg) : 0;
	}

	/**
	 * @see Log#wtf(String, String)
	 */
	public static int wtf(String msg) {
		if (DEBUG) {
			String[] wrapInfo = wrapInfo(msg);
			return Log.wtf(wrapInfo[0], wrapInfo[1]);
		}
		return 0;
	}

	/**
	 * @see Log#d(String, String, Throwable)
	 */
	public static int d(String tag, String msg, Throwable tr) {
		return DEBUG ? Log.d(tag, msg, tr) : 0;
	}

	/**
	 * @see Log#d(String, String, Throwable)
	 */
	public static int d(String msg, Throwable tr) {
		if (DEBUG) {
			String[] wrapInfo = wrapInfo(msg);
			return Log.d(wrapInfo[0], wrapInfo[1], tr);
		}
		return 0;
	}

	/**
	 * @see Log#e(String, String, Throwable)
	 */
	public static int e(String tag, String msg, Throwable tr) {
		return DEBUG ? Log.e(tag, msg, tr) : 0;
	}

	/**
	 * @see Log#e(String, String, Throwable)
	 */
	public static int e(String msg, Throwable tr) {
		if (DEBUG) {
			String[] wrapInfo = wrapInfo(msg);
			return Log.e(wrapInfo[0], wrapInfo[1], tr);
		}
		return 0;
	}

	/**
	 * @see Log#i(String, String, Throwable)
	 */
	public static int i(String tag, String msg, Throwable tr) {
		return DEBUG ? Log.i(tag, msg, tr) : 0;
	}

	/**
	 * @see Log#i(String, String, Throwable)
	 */
	public static int i(String msg, Throwable tr) {
		if (DEBUG) {
			String[] wrapInfo = wrapInfo(msg);
			return Log.i(wrapInfo[0], wrapInfo[1], tr);
		}
		return 0;
	}

	/**
	 * @see Log#v(String, String, Throwable)
	 */
	public static int v(String tag, String msg, Throwable tr) {
		return DEBUG ? Log.d(tag, msg, tr) : 0;
	}

	/**
	 * @see Log#v(String, String, Throwable)
	 */
	public static int v(String msg, Throwable tr) {
		if (DEBUG) {
			String[] wrapInfo = wrapInfo(msg);
			return Log.v(wrapInfo[0], wrapInfo[1], tr);
		}
		return 0;
	}

	/**
	 * @see Log#w(String, String, Throwable)
	 */
	public static int w(String tag, String msg, Throwable tr) {
		return DEBUG ? Log.w(tag, msg, tr) : 0;
	}

	/**
	 * @see Log#w(String, String, Throwable)
	 */
	public static int wMsg(String msg, Throwable tr) {
		if (DEBUG) {
			String[] wrapInfo = wrapInfo(msg);
			return Log.w(wrapInfo[0], wrapInfo[1], tr);
		}
		return 0;
	}

	/**
	 * @see Log#wtf(String, String, Throwable)
	 */
	public static int wtf(String tag, String msg, Throwable tr) {
		return DEBUG ? Log.wtf(tag, msg, tr) : 0;
	}

	/**
	 * @see Log#wtf(String, String, Throwable)
	 */
	public static int wtfMsg(String msg, Throwable tr) {
		if (DEBUG) {
			String[] wrapInfo = wrapInfo(msg);
			return Log.wtf(wrapInfo[0], wrapInfo[1], tr);
		}
		return 0;
	}

	/**
	 * @see Log#w(String, Throwable)
	 */
	public static int w(String tag, Throwable tr) {
		return DEBUG ? Log.w(tag, tr) : 0;
	}

	/**
	 * @see Log#wtf(String, Throwable)
	 */
	public static int wtf(String tag, Throwable tr) {
		return DEBUG ? Log.wtf(tag, tr) : 0;
	}

	/**
	 * @see Log#println(int, String, String)
	 */
	public static int println(int priority, String tag, String msg) {
		return DEBUG ? Log.println(priority, tag, msg) : 0;
	}

	/**
	 * @see Log#println(int, String, String)
	 */
	public static int println(int priority, String msg) {
		if (DEBUG) {
			String[] wrapInfo = wrapInfo(msg);
			return Log.println(priority, wrapInfo[0], wrapInfo[1]);
		}
		return 0;
	}

	/**
	 * wrap {@link Log#d(String, String)} with
	 * {@link String#format(String, Object...)}
	 */
	public static int d(String tag, String format, Object... args) {
		return DEBUG ? Log.d(tag, String.format(format, args)) : 0;
	}

	/**
	 * wrap {@link Log#i(String, String)} with
	 * {@link String#format(String, Object...)}
	 */
	public static int i(String tag, String format, Object... args) {
		return DEBUG ? Log.i(tag, String.format(format, args)) : 0;
	}

	/**
	 * wrap {@link Log#v(String, String)} with
	 * {@link String#format(String, Object...)}
	 */
	public static int v(String tag, String format, Object... args) {
		return DEBUG ? Log.v(tag, String.format(format, args)) : 0;
	}

	/**
	 * wrap {@link Log#w(String, String)} with
	 * {@link String#format(String, Object...)}
	 */
	public static int w(String tag, String format, Object... args) {
		return DEBUG ? Log.w(tag, String.format(format, args)) : 0;
	}

	/**
	 * wrap {@link Log#e(String, String)} with
	 * {@link String#format(String, Object...)}
	 */
	public static int e(String tag, String format, Object... args) {
		return DEBUG ? Log.e(tag, String.format(format, args)) : 0;
	}

	/**
	 * print the current call stack trace
	 * 
	 * @param caller
	 *            where the method call, usually use <b>this</b> key word
	 */
	public static void printStackTrace(Object caller) {
		if (!DEBUG) {
			return;
		}
		StackTraceElement[] stackTraceElement = Thread.currentThread()
				.getStackTrace();
		Log.e("System.out", "--------------" + caller.toString()
				+ "--------------");
		for (StackTraceElement s : stackTraceElement) {
			System.out.println(s);
		}
	}

	private static final String TAG = "XLog";

	/**
	 * 
	 * E_{@link #printStackTrace(Object)}
	 * 
	 * @param caller
	 */
	public static void E_printStackTrace(Object caller) {
		if (!DEBUG) {
			return;
		}
		String tag = TAG;
		StackTraceElement[] stackTraceElement = Thread.currentThread()
				.getStackTrace();
		Log.d(tag, "--------------" + caller.toString() + "--------------");
		for (StackTraceElement s : stackTraceElement) {
			Log.e(tag, s.toString());
		}
	}

	/**
	 * 
	 * D_{@link #printStackTrace(Object)}
	 * 
	 * @param caller
	 */
	public static void D_printStackTrace(Object caller) {
		if (!DEBUG) {
			return;
		}
		String tag = TAG;
		StackTraceElement[] stackTraceElement = Thread.currentThread()
				.getStackTrace();
		Log.e(tag, "--------------" + caller.toString() + "--------------");
		for (StackTraceElement s : stackTraceElement) {
			Log.d(tag, s.toString());
		}
	}

	/**
	 * 
	 * I_{@link #printStackTrace(Object)}
	 * 
	 * @param caller
	 */
	public static void I_printStackTrace(Object caller) {
		if (!DEBUG) {
			return;
		}
		String tag = TAG;
		StackTraceElement[] stackTraceElement = Thread.currentThread()
				.getStackTrace();
		Log.e(tag, "--------------" + caller.toString() + "--------------");
		for (StackTraceElement s : stackTraceElement) {
			Log.i(tag, s.toString());
		}
	}

	/**
	 * 
	 * V_{@link #printStackTrace(Object)}
	 * 
	 * @param caller
	 */
	public static void V_printStackTrace(Object caller) {
		if (!DEBUG) {
			return;
		}
		String tag = TAG;
		StackTraceElement[] stackTraceElement = Thread.currentThread()
				.getStackTrace();
		Log.e(tag, "--------------" + caller.toString() + "--------------");
		for (StackTraceElement s : stackTraceElement) {
			Log.v(tag, s.toString());
		}
	}

	/**
	 * 
	 * W_{@link #printStackTrace(Object)}
	 * 
	 * @param caller
	 */
	public static void W_printStackTrace(Object caller) {
		if (!DEBUG) {
			return;
		}
		String tag = TAG;
		StackTraceElement[] stackTraceElement = Thread.currentThread()
				.getStackTrace();
		Log.e(tag, "--------------" + caller.toString() + "--------------");
		for (StackTraceElement s : stackTraceElement) {
			Log.w(tag, s.toString());
		}
	}

}
