"""
Parker Buszka 
"""

import numpy as np
import matplotlib.pyplot as plt
from sklearn.model_selection import train_test_split
from sklearn.neighbors import KNeighborsClassifier
import math 
from sklearn.model_selection import StratifiedKFold
from statistics import mean

# Code for Exercise 1
arr = np.loadtxt("Real estate valuation data set.csv", delimiter=',', dtype=str)
real_estate = []


for i in arr:
    k = i[1:len(i)]
    k = k.tolist()
    real_estate.append(k)
    

real_estate = np.array(real_estate)
features = real_estate[0]
cancer = np.delete(real_estate, 0, axis=0)
X, y = real_estate[:, 1:len(real_estate)], real_estate[:, [0]]

# Code for Exercise 2
X = np.array(X)
y = np.array(y)


print(f'length of samples: {len(X)}, lengths of predictors: {len(y)}, number of targets: 2')

# Code for Exercise 3
print(f'number of features: 30 \nnames of features: \n{features}')
print(f'name of target: Diagnosis\nnumber of samples: {len(X)} \nfirst five rows of data:\n {X[0:5]}\n')

new_y = []
for i in y:
    if i == 'M':
        new_y.append(1)
    else:
        new_y.append(0)

y = np.array(new_y)

plt.figure(1)
plt.subplot(111)
plt.hist(X[:, 0], color=["blue"], bins=10, edgecolor='white', linewidth=5)
plt.ylabel('frequency')
plt.xlabel('radius1')
plt.xticks([])
plt.yticks([])

plt.figure(2)
plt.subplot(111)
plt.hist(X[:, 1], color=["blue"], edgecolor='white', linewidth=5)
plt.ylabel('frequency')
plt.xlabel('texture1')
plt.xticks([])
plt.yticks([])

plt.figure(3)
plt.title('Scatter Plot Breast Cancer')
# added some transparency to the dots to make it more clear the correlation
plt.scatter(X[y == 1, 0], X[y == 1, 1], c='orange', label='Malignant', alpha=.5) 
plt.scatter(X[y == 0, 0], X[y == 0, 1], c='green', label='Benign', alpha=.5)
plt.xlabel('radius1')
plt.ylabel('texture1')
plt.xticks([])
plt.yticks([])
plt.legend(loc='upper right')
plt.show()

# Code for Exercise 4

new_X = []
temp_arr = []

for i in X:
    for j in i:
        temp = float(j)
        temp_arr.append(temp)
    new_X.append(temp_arr)
    temp_arr = []

X = np.array(new_X)

X_train, X_test, y_train, y_test = train_test_split(X, y, stratify=y, random_state=42)
training_accuracy = []
test_accuracy = []

n = len(y)

neighbors_settings = range(1, int((math.sqrt(n)+3)), 2)

plt.figure(1)
for n_neighbors in neighbors_settings:
    clf = KNeighborsClassifier(n_neighbors=n_neighbors)
    clf.fit(X_train, y_train)
    training_accuracy.append(clf.score(X_train, y_train))
    test_accuracy.append(clf.score(X_test, y_test))

plt.plot(neighbors_settings, training_accuracy, label="training accuracy")
plt.plot(neighbors_settings, test_accuracy, label="test accuracy")
plt.ylabel("Accuracy")
plt.xlabel("n_neighbors")
plt.legend()
print("13 neighbors gives the best testing accuracy")

# Code for Exercise 5

k_folds = [2, 3, 4, 5]

for folds in k_folds:
    training_accuracy = []
    test_accuracy = []
    stratified_kf = StratifiedKFold(n_splits=folds, shuffle=True, random_state=42)

    for train_index, test_index in stratified_kf.split(X, y):
        X_train, X_test = X[train_index], X[test_index]
        y_train, y_test = y[train_index], y[test_index]

        clf = KNeighborsClassifier(n_neighbors=11)
        clf.fit(X_train, y_train)
        training_accuracy.append(clf.score(X_train, y_train))
        test_accuracy.append(clf.score(X_test, y_test))

print(f"training_accuracy: {training_accuracy}")
print(f'mean training accuracy: {mean(training_accuracy)}')
print(f"test_accuracy: {test_accuracy}")
print(f'mean test accuracy: {mean(test_accuracy)}')
