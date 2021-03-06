package it.uniroma1.dis.wsngroup.gexf4j.core.impl.writer;

import it.uniroma1.dis.wsngroup.gexf4j.core.Node;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.util.List;


public class ParentsEntityWriter extends AbstractEntityWriter<List<Node>>{
	private static final String ENTITY = "parents";
	
	public ParentsEntityWriter(XMLStreamWriter writer, List<Node> entity) {
		super(writer, entity);
		
		if (!entity.isEmpty()) {
			write();
		}
	}

	@Override
	protected String getElementName() {
		return ENTITY;
	}

	@Override
	protected void writeElements() throws XMLStreamException {
		for (Node n : entity) {
			new ParentEntityWriter(writer, n);
		}
	}

	@Override
	protected void writeAttributes() throws XMLStreamException {
		// do nothing
	}
}