/**
 * 
 */
package weka.classifiers.meta;

import java.util.Iterator;

import weka.core.Capabilities;
import weka.core.Instances;
import weka.core.Capabilities.Capability;

/**
 * MultiSearch Classifier that accapts empty dataset;
 * @author pawel trajdos
 * @since 0.0.1
 * @version 0.0.1
 *
 */
public class MultiSearchZeroInstances extends MultiSearch {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3120999809867764212L;

	/**
	 * 
	 */
	public MultiSearchZeroInstances() {
		super();
	}

	@Override
	public Capabilities getCapabilities() {
		Capabilities 	result;
	    
	    result = super.getCapabilities();
	    result.setMinimumNumberInstances(0);
		
	    return result;
	}

	@Override
	public void buildClassifier(Instances data) throws Exception {
		int numInstances = data.numInstances();
		if(numInstances == 0) {
			this.m_Classifier.buildClassifier(data);
			return;
		}
		super.buildClassifier(data);
	}
	
	

}
