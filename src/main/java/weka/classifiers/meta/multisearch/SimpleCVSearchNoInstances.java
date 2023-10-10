package weka.classifiers.meta.multisearch;

import weka.core.Instances;
import weka.core.setupgenerator.Space;

/**
 * @author pawel trajdos
 * @since 0.1.0
 * @version 0.1.0
 *
 */

public class SimpleCVSearchNoInstances extends SimpleCVSearch {

	private static final long serialVersionUID = -8715753537188967729L;

	public SimpleCVSearchNoInstances() {
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
