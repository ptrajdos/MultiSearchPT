package weka.classifiers.meta.multisearch;

/**
 * @author pawel trajdos
 * @since 0.1.0
 * @version 0.1.0
 *
 */
public class SimpleCVSearchTest extends AbstractSearchTest {

	@Override
	protected AbstractSearch getSearchAlgorithm() {
		SimpleCVSearch search =  new SimpleCVSearch();
		return search;
	}

	@Override
	public void testNoInstances() {
		//Intentionally does nothing!
	}

	@Override
	public void testSearchSigleObject() {
		//Intentionally does nothing!
	}
	
	
	

}
