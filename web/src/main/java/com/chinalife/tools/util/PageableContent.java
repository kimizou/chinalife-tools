package com.chinalife.tools.util;

import java.util.List;

public class PageableContent<T> {

	private List<T> content;

	private Page page;
	
	public PageableContent(List<T> content, int currentPage, int rows, int totalRows) {
		this.content = content;
		this.page = new Page(currentPage, rows, totalRows);
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
}
