package edu.gatech.quirkyqwerties.spacetraders.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
/**
 * model that the table can use
 * @author Quirky Qwertys
 *
 * @param <T> generic type
 */
public abstract class ListTableModel<T> implements TableModel,List<T> {
	private List<T> tableList;
	private String[] columnNames;
	private List<TableModelListener> listeners;
	
	/**
	 * constructor to set up the column names and provide the list
	 * @param columnNames
	 * @param source
	 */
	public ListTableModel(String[] columnNames, List<T> source){
		tableList = source;
		this.columnNames = columnNames;
		listeners = new ArrayList<TableModelListener>();
		
		
	}

	@Override
	public boolean add(T e) {
		boolean added = tableList.add(e);
		notifyChange(tableList.size()-1, tableList.size()-1, TableModelEvent.INSERT );
		return added;
	}

	@Override
	public void add(int index, T element) {
		tableList.add(index,element);
		notifyChange(index, index, TableModelEvent.INSERT );
		
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		int indexFirst = tableList.size();
		boolean added = tableList.addAll(c);
		int indexLast = tableList.size()-1;
		notifyChange(indexFirst, indexLast, TableModelEvent.INSERT );
		return added;
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		boolean added = tableList.addAll(index, c);
		int indexLast = index+c.size();
		notifyChange(index, indexLast, TableModelEvent.INSERT );
		return added;
	}

	@Override
	public void clear() {
		
		int indexLast = tableList.size()-1;
		
		tableList.clear();
		if (indexLast>=0){
			notifyChange(0,indexLast,TableModelEvent.DELETE);
		}
		
		
	}

	@Override
	public boolean contains(Object o) {
		
		return tableList.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		
		return tableList.containsAll(c);
	}

	@Override
	public T get(int index) {
		return tableList.get(index);
	}

	@Override
	public int indexOf(Object o) {
		return tableList.indexOf(o);
	}

	@Override
	public boolean isEmpty() {
		return tableList.isEmpty();
	}

	@Override
	public Iterator<T> iterator() {
		return tableList.iterator();
	}

	@Override
	public int lastIndexOf(Object o) {
		
		return tableList.lastIndexOf(o);
	}

	@Override
	public ListIterator<T> listIterator() {
		return tableList.listIterator();
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		return tableList.listIterator(index);
	}

	@Override
	public boolean remove(Object o) {
		int index = tableList.indexOf(o);
		boolean removed = tableList.remove(o);
		notifyChange(index,index,TableModelEvent.DELETE);
		return removed;
	}

	@Override
	public T remove(int index) {
		T removed = tableList.remove(index);
		notifyChange(index,index,TableModelEvent.DELETE);
		return removed;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		boolean removed = tableList.removeAll(c);
		notifyChange();
		return removed;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		boolean retained =  tableList.retainAll(c);
		notifyChange();
		return retained;
	}

	@Override
	public T set(int index, T element) {
		T value = tableList.set(index,element);
		notifyChange(index, index, TableModelEvent.UPDATE);
		return value;
	}

	@Override
	public int size() {
		return tableList.size();
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		return tableList.subList(fromIndex, toIndex);
	}

	@Override
	public Object[] toArray() {
		return tableList.toArray();
	}

	@Override
	public <K> K[] toArray(K[] a) {
		return tableList.toArray(a);
	}

	@Override
	public void addTableModelListener(TableModelListener listener) {
		listeners.add(listener);
		
	}
	@Override
	public void removeTableModelListener(TableModelListener listener) {
		listeners.remove(listener);
		
	}
	
	/**
	 * notify change if the table changes
	 * @param firstRow
	 * @param lastRow
	 * @param op
	 */
	protected void notifyChange(int firstRow, int lastRow, int op){
		TableModelEvent event = new TableModelEvent(this, firstRow, lastRow, TableModelEvent.ALL_COLUMNS, op);
		notifyChange(event);
	}
	
	/**
	 * notify if the table changes
	 * @param event
	 */
	protected void notifyChange(TableModelEvent event){
		
		for (TableModelListener listener: listeners){
			listener.tableChanged(event);
		}
		
	}
	/**
	 * notify listeners of table changes
	 */
	protected void notifyChange(){
		
		notifyChange(new TableModelEvent(this));
	}

	@Override
	public Class<?> getColumnClass(int arg0) {
		return Object.class;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public String getColumnName(int colIndex) {
		return columnNames[colIndex];
	}

	@Override
	public int getRowCount() {
		return tableList.size();
	}

	@Override
	public abstract Object getValueAt(int row, int col);

	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	}

	@Override
	public void setValueAt(Object arg0, int arg1, int arg2) {
		
	}

	
}
