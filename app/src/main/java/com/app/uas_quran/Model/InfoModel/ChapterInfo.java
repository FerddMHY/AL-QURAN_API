package com.app.uas_quran.Model.InfoModel;

import com.google.gson.annotations.SerializedName;

public class ChapterInfo{

	@SerializedName("short_text")
	private String shortText;

	@SerializedName("id")
	private int id;

	@SerializedName("chapter_id")
	private int chapterId;

	@SerializedName("language_name")
	private String languageName;

	@SerializedName("source")
	private String source;

	@SerializedName("text")
	private String text;

	public String getShortText(){
		return shortText;
	}

	public int getId(){
		return id;
	}

	public int getChapterId(){
		return chapterId;
	}

	public String getLanguageName(){
		return languageName;
	}

	public String getSource(){
		return source;
	}

	public String getText(){
		return text;
	}

	@Override
 	public String toString(){
		return 
			"ChapterInfo{" + 
			"short_text = '" + shortText + '\'' + 
			",id = '" + id + '\'' + 
			",chapter_id = '" + chapterId + '\'' + 
			",language_name = '" + languageName + '\'' + 
			",source = '" + source + '\'' + 
			",text = '" + text + '\'' + 
			"}";
		}
}