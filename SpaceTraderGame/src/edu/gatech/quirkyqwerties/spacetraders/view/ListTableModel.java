/**
 * ListTableModel.java
 * @version 1.0
 * copyright 2012
 */
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
 * @version 1.0
 * @param <T> generic type
 */
public abstract class ListTableModel<T> implements TableModel, List<T> {
	
	/** the list representing the table */
	private final List<T> tableList;
	
	/** column names in the table */
	private final String[] columnNames;
	
	/** listeners for the table model */
	private final List<TableModelListener> listeners;
	
	/**
	 * constructor to set up the column names and provide the list
	 * @param columnNames
	 * @param source
	 */
	public ListTableModel(String[] columnNames, List<T> source){
		tableList = source;
		this.columnNames = columnNames; // $codepro.audit.disable com.instantiations.assist.eclipse.arrayIsStoredWithoutCopying
		listeners = new ArrayList<TableModelListener>();
		
		
	}
	
	/**
	 * add an item to the table model
	 * @param e
	 * @return boolean
	 */
	@Override
	public boolean add(T e) {
		final boolean added = tableList.add(e);
		notifyChange(tableList.size() - 1, tableList.size() - 1, TableModelEvent.INSERT );
		return added;
	}

	/**
	 * add an item to the table model at a certain index
	 * @param index
	 * @param element
	 */
	@Override
	public void add(int index, T element) {
		tableList.add(index, element);
		notifyChange(index, index, TableModelEvent.INSERT );
		
	}

	/**
	 * add all items in the collection to the table model
	 * @param c
	 * @return boolean
	 */
	@Override
	public boolean addAll(Collection<? extends T> c) {
		final int indexFirst = tableList.size();
		final boolean added = tableList.addAll(c);
		final int indexLast = tableList.size() - 1;
		notifyChange(indexFirst, indexLast, TableModelEvent.INSERT );
		return added;
	}

	/**
	 * adds all elements starting with a certain index
	 * @param index
	 * @param c
	 * @return boolean
	 */
	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		final boolean added = tableList.addAll(index, c);
		final int indexLast = index + c.size();
		notifyChange(index, indexLast, TableModelEvent.INSERT );
		return added;
	}

	/**
	 * clears the table model
	 */
	@Override
	public void clear() {
		
		final int indexLast = tableList.size() - 1;
		
		tableList.clear();
		if (indexLast >= 0){
			notifyChange(0, indexLast, TableModelEvent.DELETE);
		}
		
		
	}

	/**
	 * checks to see if an item is in the list
	 * @param o
	 * @return boolean
	 */
	@Override
	public boolean contains(Object o) {
		
		return tableList.contains(o);
	}

	/**
	 * checks to see if all items are in the table model
	 * @param c
	 * @return boolean
	 */
	@Override
	public boolean containsAll(Collection<?> c) {
		
		return tableList.containsAll(c);
	}

	/**
	 * gets the item at a certain index
	 * @param index
	 * @return T
	 */
	@Override
	public T get(int index) {
		return tableList.get(index);
	}

	/**
	 * gets the index of an object
	 * @param o
	 * @return int
	 */
	@Override
	public int indexOf(Object o) {
		return tableList.indexOf(o);
	}

	/**
	 * checks if the table list is empty
	 * @return boolean
	 */
	@Override
	public boolean isEmpty() {
		return tableList.isEmpty();
	}

	/**
	 * gets an iterator for the table list
	 * @return Iterator
	 */
	@Override
	public Iterator<T> iterator() {
		return tableList.iterator();
	}

	/**
	 * gets the last index of an object
	 * @param o
	 * @return int
	 */
	@Override
	public int lastIndexOf(Object o) {
		
		return tableList.lastIndexOf(o);
	}
	
	/**
	 * gets a list iterator
	 * @return ListIterator
	 */
	@Override
	public ListIterator<T> listIterator() {
		return tableList.listIterator();
	}

	/**
	 * gets a list iterator at a certain index
	 * @param index
	 * @return ListIterator<T>
	 */
	@Override
	public ListIterator<T> listIterator(int index) {
		return tableList.listIterator(index);
	}

	/**
	 * removes an object
	 * @param o
	 * @return boolean
	 */
	@Override
	public boolean remove(Object o) {
		final int index = tableList.indexOf(o);
		final boolean removed = tableList.remove(o);
		notifyChange(index, index, TableModelEvent.DELETE);
		return removed;
	}

	/**
	 * removes an object at a particular index
	 * @param index
	 * @return T
	 */
	@Override
	public T remove(int index) { // $codepro.audit.disable overloadedMethods
		final T removed = tableList.remove(index);
		notifyChange(index, index, TableModelEvent.DELETE);
		return removed;
	}

	/**
	 * removes all objects in the collection
	 * @param c
	 * @return boolean
	 */
	@Override
	public boolean removeAll(Collection<?> c) {
		final boolean removed = tableList.removeAll(c);
		notifyChange();
		return removed;
	}

	/**
	 * retains only the objects in the collection
	 * @param c
	 * @return boolean
	 */
	@Override
	public boolean retainAll(Collection<?> c) {
		final boolean retained =  tableList.retainAll(c);
		notifyChange();
		return retained;
	}

	/**
	 * sets an object to an index
	 * @param index
	 * @param element
	 * @return T
	 */
	@Override
	public T set(int index, T element) {
		final T value = tableList.set(index, element);
		notifyChange(index, index, TableModelEvent.UPDATE);
		return value;
	}

	/**
	 * sets the table size
	 * @return int
	 */
	@Override
	public int size() {
		return tableList.size();
	}

	/**
	 * gets a sublist
	 * @param fromIndex
	 * @param toIndex
	 * @return List<T>
	 */
	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		return tableList.subList(fromIndex, toIndex);
	}

	/**
	 * converts the list to an array
	 * @return Object[]
	 */
	@Override
	public Object[] toArray() {
		return tableList.toArray();
	}

	/**
	 * converts to an array of specified type
	 * @param a
	 * @return <K> K[]
	 */
	@Override
	public <K> K[] toArray(K[] a) { // $codepro.audit.disable overloadedMethods , methodJavadoc
		return tableList.toArray(a);
	}

	/**
	 * adds a listener to the table model
	 * @param listener
	 */
	@Override
	public void addTableModelListener(TableModelListener listener) {
		listeners.add(listener);
		
	}
	
	/**
	 * removes a listener from the table model
	 * @param listener
	 */
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
		final TableModelEvent event = new TableModelEvent(this, firstRow, 
				lastRow, TableModelEvent.ALL_COLUMNS, op);
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

	/**
	 * get the class of the column
	 * @param arg0
	 * @return Class<?>
	 */
	@Override
	public Class<?> getColumnClass(int arg0) {
		return Object.class;
	}

	/**
	 * gets the count of the columns
	 * @return int
	 */
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	/**
	 * gets the name of the column at a certain index
	 * @param colIndex
	 * @return String
	 */
	@Override
	public String getColumnName(int colIndex) {
		return columnNames[colIndex];
	}

	/**
	 * gets the num of rows
	 * @return int
	 */
	@Override
	public int getRowCount() {
		return tableList.size();
	}

	/**
	 * gets the value at a row/col
	 * @param row
	 * @param col
	 * @return Object
	 */
	@Override
	public abstract Object getValueAt(int row, int col);

	/**
	 * checks if the cell is editable
	 * @param row
	 * @param col
	 * @return boolean
	 */
	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	}

	/**
	 * sets the value of a cell in the table
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 */
	@Override
	public void setValueAt(Object arg0, int arg1, int arg2) { // $codepro.audit.disable emptyMethod
		
	}

	
}
