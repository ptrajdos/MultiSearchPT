/**
 * 
 */
package weka.classifiers.meta.multisearch;

import weka.classifiers.Classifier;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.instance.Resample;

/**
 * @author pawel trajdos
 * @since 0.1.0
 * @version 0.1.0
 *
 */
public class SimpleCVSearch extends DefaultSearch {

	private static final long serialVersionUID = 7885540058437652612L;

	/**
	 * This class simple does not perform second stage search.
	 */
	public SimpleCVSearch() {
		super();
	}

	@Override
	protected Performance findBest(Instances inst) throws Exception {
		
		Performance		result;
	    Instances		sample;
	    Resample 		resample;
	    Classifier		cls;

	    log("Step 1:\n");

	    // generate sample?
	    if (getSampleSizePercent() == 100) {
	      sample = inst;
	    }
	    else {
	      log("Generating sample (" + getSampleSizePercent() + "%)");
	      resample = new Resample();
	      resample.setRandomSeed(retrieveOwner().getSeed());
	      resample.setSampleSizePercent(getSampleSizePercent());
	      resample.setInputFormat(inst);
	      sample = Filter.useFilter(inst, resample);
	    }


	    // find first center
	    log("\n=== Final space - Start ===");
	    result = determineBestInSpace(m_Space, sample, m_InitialSpaceTestInst, m_InitialSpaceNumFolds, true);
	    log("\nResult of Step 1: " + result + "\n");
	    log("=== Initial space - End ===\n");

	    return result;
	}
	
	

}
