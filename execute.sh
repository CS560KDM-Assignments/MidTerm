#!/bin/sh
i="9";
ip_file=""

java -cp target/twitter-naive-bayes-example-1.0-jar-with-dependencies.jar com.chimpler.example.bayes.TweetTSVToSeq ../../data/heartDiseaseIdFullData.txt ../../data/heart-seq$i

#java -cp target/twitter-naive-bayes-example-1.0-jar-with-dependencies.jar com.chimpler.example.bayes.TweetTSVToSeq data/sample ../../data/heart-seq$i

hadoop fs -put ../../data/heart-seq$i

mahout seq2sparse -i heart-seq$i -o heart-vec$i

mahout split -i heart-vec$i/tfidf-vectors --trainingOutput heart-train-vec$i --testOutput heart-tst-vec$i --randomSelectionPct 40 --overwrite --sequenceFiles -xm sequential

mahout trainnb -i heart-train-vec$i -el -li labelindex-heart$i -o model-heart$i -ow -c

mahout testnb -i heart-tst-vec$i -l labelindex-heart$i -m model-heart$i -ow -o heart-result$i -c
echo $i





