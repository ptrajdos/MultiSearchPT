package weka.classifiers.meta.multisearch;

import junit.framework.TestCase;
import weka.classifiers.Classifier;
import weka.classifiers.lazy.IBk;
import weka.classifiers.meta.MultiSearch;
import weka.classifiers.meta.MultiSearchZeroInstances;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SelectedTag;
import weka.core.setupgenerator.AbstractParameter;
import weka.core.setupgenerator.MathParameter;
import weka.tools.data.RandomDataGenerator;
import weka.tools.tests.DistributionChecker;

public class DefaultSerachNoInstancesTest extends TestCase {
	
	protected DefaultSerachNoInstances getSearchAlgorithm() {
		DefaultSerachNoInstances defNo  = new DefaultSerachNoInstances();
		return defNo;
	}
	
	protected Classifier getClassifier() {
		
		MathParameter mParam = new MathParameter();
		mParam.setProperty("KNN");
		mParam.setMin(1);
		mParam.setMax(5);
		mParam.setStep(1);
		mParam.setExpression("I");
		
		MultiSearch ms = new MultiSearchZeroInstances();
		ms.setClassifier(new IBk());
		
		ms.setSearchParameters(new AbstractParameter[]{mParam});
		DefaultEvaluationMetrics evMetr = new DefaultEvaluationMetrics();
		ms.setEvaluation(new SelectedTag(DefaultEvaluationMetrics.EVALUATION_KAPPA, evMetr.getTags()));
		ms.setDebug(true);
		ms.setAlgorithm(getSearchAlgorithm());
		
		return ms;
	}
	
	public void performclassifierTest(Instances data) {
		Instance testInstance = data.get(0);
		Classifier ms = this.getClassifier();
		
		try {
			ms.buildClassifier(data);
			double[] distribution = ms.distributionForInstance(testInstance);
			assertTrue("Correct predistion", DistributionChecker.checkDistribution(distribution));
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception has been caught: "+ e.getLocalizedMessage());
		}
	}
	 
	public void testSearch() {
		RandomDataGenerator gen = new RandomDataGenerator();
		Instances data = gen.generateData();
		this.performclassifierTest(data);
		
		gen.setNumObjects(5);
		data  =gen.generateData();
		this.performclassifierTest(data);
		
		gen.setNumObjects(1);
		data  =gen.generateData();
		this.performclassifierTest(data);
		
	
	}
	
	public void testNoInstances() {
		RandomDataGenerator gen = new RandomDataGenerator();
		Instances data = gen.generateData();
		gen.setNumObjects(0);
		data  =gen.generateData();
		
		Classifier ms = this.getClassifier();
		try {
			ms.buildClassifier(data);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception has been caught: "+ e.getLocalizedMessage());
		}
		
		
	}

	
}
