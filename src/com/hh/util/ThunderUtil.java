package com.hh.util;

import java.util.ArrayList;
import java.util.List;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.Structure;
import com.sun.jna.WString;

public interface ThunderUtil extends Library {
	ThunderUtil instance = (ThunderUtil)Native.loadLibrary("D:/workspace_Eclipse/自己动手写网络爬虫/focused_crawler_video/thunder/xldl.dll", ThunderUtil.class);
	
	public static class DownTaskParam extends Structure {
		public int nReserved;
		public char[] szTaskUrl;	// 任务URL
		public char[] szRefUrl;		// 引用页
		public char[] szCookies;	// 浏览器cookie
		public char[] szFilename;	// 下载保存文件名.
		public char[] szReserved0;
		public char[] szSavePath;	// 文件保存目录
		public long hReserved;
		public byte bReserved;
		public char[] szReserved1;
		public char[] szReserved2;
		public byte IsOnlyOriginal;	 // 是否只从原始地址下载
		public long nReserved1;
		public byte DisableAutoRename;
		public byte IsResume;		// 禁止智能命名
		public long[] reserved;		// 是否用续传
		
		public DownTaskParam() {
			szTaskUrl = new char[2084];
			szRefUrl = new char[2084];
			szCookies = new char[4096];
			szFilename = new char[260];
			szReserved0 = new char[260];
			szSavePath = new char[260];
			szReserved1 = new char[64];
			szReserved2 = new char[64];
			reserved = new long[2048];
			
			nReserved1 = 5;
			bReserved = 0;
			DisableAutoRename = 0;
			IsOnlyOriginal = 0;
			IsResume = 1;
		}
		
		public static class ByReference extends DownTaskParam implements Structure.ByReference { 
		}
		
		public static class ByValue extends DownTaskParam implements Structure.ByValue { 
		}

		@Override
		protected List<String> getFieldOrder() {
			List<String> a = new ArrayList<String>();
			a.add("nReserved");
			a.add("szTaskUrl");
			a.add("szRefUrl");
			a.add("szCookies");
			a.add("szFilename");
			a.add("szReserved0");
			a.add("szSavePath");
			a.add("hReserved");
			a.add("bReserved");
			a.add("szReserved1");
			a.add("szReserved2");
			a.add("IsOnlyOriginal");
			a.add("nReserved1");
			a.add("DisableAutoRename");
			a.add("IsResume");
			a.add("reserved");
			return a;
		}
		
	}
	
	public boolean XL_Init();
	public boolean XL_UnInit();
	public long XL_CreateTask(DownTaskParam.ByValue stParam); 
	public byte XL_StartTask(long hTask);
	public NativeLong XL_CreateTaskByThunder(WString pszUrl, WString pszFileName, WString pszReferUrl, WString pszCharSet, WString pszCookie);
	
}
