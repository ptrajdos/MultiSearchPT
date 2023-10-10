package weka.classifiers.meta.multisearch;

/**
 * @author pawel trajdos
 * @since 0.0.1
 * @version 0.1.0
 *
 */
public class DefaultSerachNoInstancesTest extends AbstractSearchTest {
	
	@Override
	protected AbstractSearch getSearchAlgorithm() {
		DefaultSerachNoInstances defNo  = new DefaultSerachNoInstances();
		return defNo;
	}

	
}
