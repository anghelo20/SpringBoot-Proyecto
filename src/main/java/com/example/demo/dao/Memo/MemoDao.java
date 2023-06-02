package com.example.demo.dao.Memo;

import java.util.List;

import com.example.demo.model.Memo;
import com.example.demo.model.Person;

public interface MemoDao {
	
public List<Memo> getMemosActive();
	
	public void createMemo(Person person);
	
	public void deleteMemo(Long id);
	
	public void updateMemo(Memo memo);
	
	public int getNumberMemos ();

}
