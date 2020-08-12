package weka.classifiers.meta;

import junit.framework.Test;
import junit.framework.TestSuite;
import weka.classifiers.AbstractClassifierTest;
import weka.classifiers.Classifier;
import weka.classifiers.lazy.IBk;
import weka.classifiers.meta.multisearch.DefaultEvaluationMetrics;
import weka.classifiers.meta.multisearch.DefaultSerachNoInstances;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SelectedTag;
import weka.core.setupgenerator.AbstractParameter;
import weka.core.setupgenerator.MathParameter;
import weka.tools.data.RandomDataGenerator;
import weka.tools.tests.DistributionChecker;

public class MultiSearchZeroInstancesTest extends AbstractClassifierTest {

	public MultiSearchZeroInstancesTest(String name) {
		super(name);
	}
	
	protected DefaultSerachNoInstances getSearchAlgorithm() {
		DefaultSerachNoInstances defNo  = new DefaultSerachNoInstances();
		return defNo;
	}

	@Override
	public Classifier getClassifier() {
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
	
	public void testNoTraininstances(){
		 RandomDataGenerator gen = new RandomDataGenerator();
		 gen.setNumObjects(0);
		 Instances data = gen.generateData();
		 
		 Classifier cla = this.getClassifier();
		 
		 try {
			cla.buildClassifier(data);
			
			gen.setNumObjects(10);
			Instances data2 = gen.generateData();
			Instance test = data2.get(0);
			
			double[] distribution  = cla.distributionForInstance(test);
			assertTrue("Distribution check: ", DistributionChecker.checkDistribution(distribution));
			
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception has been caught: " + e.getLocalizedMessage());
		}
	 }
	
	 public static Test suite() {
		    return new TestSuite(MultiSearchZeroInstancesTest.class);
	 }
	 
	 
	
	 
	 
	 public static void main(String[] args){
		    junit.textui.TestRunner.run(suite());
	}

}
