/**
 * 
 */
package weka.classifiers.meta.multisearch;

import weka.core.Instances;
import weka.core.setupgenerator.Space;

/**
 * @author pawel trajdos
 * @since 0.0.1
 * @version 0.0.1
 *
 */
public class DefaultSerachNoInstances extends DefaultSearch {

	/**
	 * 
	 */
	private static final long serialVersionUID = 282777242650873303L;

	/**
	 * 
	 */
	public DefaultSerachNoInstances() {
		super();
	}

	@Override
	protected Performance determineBestInSpace(Space space, Instances train, Instances test, int folds,
			boolean postClean) throws Exception {
		
		if(test == null) {
			int numTrainInstances = train.numInstances();
			if(folds > numTrainInstances) {
				this.log("Not enough instances to perform crossvalidation. Testing on training set!");
				return super.determineBestInSpace(space, train, test, 1, postClean);
			}
			
		}
		
		return super.determineBestInSpace(space, train, test, folds, postClean);
	}
	
	

}
