package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.vo.Item3;

public interface IItemService3 {
	public void register(Item3 item);
	public List<Item3> list();
	public Item3 read(int itemId);
	public List<String> getAttach(int itemId);
	public void modify(Item3 item);
	public void remove(int itemId);
}
