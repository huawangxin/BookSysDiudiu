package com.youdian.jcompz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.youdian.dao.Dao;
import com.youdian.model.BookType;

public class MapPz {
	static Map map = new HashMap();

	public static Map getMap() {
		List list = Dao.selectBookCategory();
		for (int i = 0; i < list.size(); i++) {
			BookType booktype = (BookType) list.get(i);

			Item item = new Item();
			item.setId(booktype.getId());
			item.setName(booktype.getType());
			map.put(String.valueOf(item.getId()), item);

		}
		return map;
	}
}
