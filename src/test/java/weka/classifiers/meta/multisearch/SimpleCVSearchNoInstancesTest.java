package weka.classifiers.meta.multisearch;

/**
 * @author pawel trajdos
 * @since 0.1.0
 * @version 0.1.0
 *
 */
public class SimpleCVSearchNoInstancesTest extends AbstractSearchTest {

	@Override
	protected AbstractSearch getSearchAlgorithm() {
		SimpleCVSearchNoInstances search =  new SimpleCVSearchNoInstances();
		return search;
	}

	

}
