package com.app.uas_quran.Model.InfoModel;

import com.google.gson.annotations.SerializedName;

public class Info{

	@SerializedName("chapter_info")
	private ChapterInfo chapterInfo;

	public ChapterInfo getChapterInfo(){
		return chapterInfo;
	}

	@Override
 	public String toString(){
		return 
			"Info{" + 
			"chapter_info = '" + chapterInfo + '\'' + 
			"}";
		}
}