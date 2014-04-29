package algorithm;

public class Gene {
	
	private int index;
	private boolean editable;
	private boolean gene;

	public Gene ()
	{
		index = -1;
		editable = true;
		gene = false;	
	}
	
	@Override
	public String toString() {
		return "Gene [index=" + index + ", editable=" + editable + ", gene="
				+ gene + "]";
	}

	public Gene (Gene gene)
	{
		index = gene.index;
		editable = gene.editable;
		this.gene = gene.gene;	
	}

	/**
	 * @return the editable
	 */
	public boolean isEditable() {
		return editable;
	}
	/**
	 * @param editable the editable to set
	 */
	public void setEditable() {
		this.editable = true;
	}
	
	public void setUnEditable() {
		this.editable = false;
	}
	/**
	 * @return the gene
	 */
	public boolean isGene() {
		return gene;
	}
	/**
	 * @param gene the gene to set
	 */
	public void setGene(int index) {
		this.gene = true;
		setIndex(index);
	}
	
	public void clrGene() {
		this.gene = false;
		setIndex(-1);
	}

	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}
}