package com.anne.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Meta {
	@JsonIgnore
	private boolean is_end;
	@JsonIgnore
	private int pageable_count;
	@JsonIgnore
	private int total_count;
	private int[] page_idx;
	private int curr_idx;
	private int page_pre;
	private int page_next;

	public Meta(final boolean is_end, final int pageable_count, final int total_count) {
		this.is_end = is_end;
		this.pageable_count = pageable_count;
		this.total_count = total_count;
	}

	public boolean isIs_end() {
		return is_end;
	}

	public void setIs_end(boolean is_end) {
		this.is_end = is_end;
	}

	public int getPageable_count() {
		return pageable_count;
	}

	public void setPageable_count(int pageable_count) {
		this.pageable_count = pageable_count;
	}

	public int getTotal_count() {
		return total_count;
	}

	public void setTotal_count(int total_count) {
		this.total_count = total_count;
	}

	public int[] getPage_idx() {
		return page_idx;
	}

	public void setPage_idx(int[] page_idx) {
		this.page_idx = page_idx;
	}

	public int getCurr_idx() {
		return curr_idx;
	}

	public void setCurr_idx(int curr_idx) {
		this.curr_idx = curr_idx;
	}

	public int getPage_pre() {
		return page_pre;
	}

	public void setPage_pre(int page_pre) {
		this.page_pre = page_pre;
	}

	public int getPage_next() {
		return page_next;
	}

	public void setPage_next(int page_next) {
		this.page_next = page_next;
	}

}