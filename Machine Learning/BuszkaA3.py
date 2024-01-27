import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from sklearn.preprocessing import StandardScaler
from sklearn.model_selection import GridSearchCV, train_test_split
from sklearn.linear_model import Ridge, Lasso, LinearRegression, LogisticRegression
from sklearn.metrics import mean_squared_error, r2_score, roc_curve, auc, precision_score, recall_score, confusion_matrix


arr = np.loadtxt("Real estate valuation data set.csv", delimiter=',', dtype=str)
real_estate = []

features = arr[0, 1:7]
target_value = arr[0,-1]

X = arr[1:, 1:7]
X = X.astype(float)
y = arr[1:, -1]
y = y.astype(float)
real_estate = arr[1:, 1:]

# Meet the Data Section
print(f'Feature Count: {len(features)}')
print(f'Feature names: {features}')
print(f'Name of target: {target_value}')
print(f'Number of samples: {len(y)}')
print("Description of data: This data include features and target values regarding to Boston house price per unit area.")
print(f'First 5 Rows of data: \n {X[:5]}')

#Creating the correlation matrix
df = pd.DataFrame(data = X, columns=['X1 transaction date',	'X2 house age',	'X3 distance to the nearest MRT station', 'X4 number of convenience stores', 'X5 latitude',	'X6 longitude'])
corr_matrix = df.corr()
print(f'\n{corr_matrix}\n')

#Scaling the Data
X_scaled = StandardScaler().fit_transform(X)

#Splitting the Data
X_train, X_test, y_train, y_test = train_test_split(X_scaled, y, test_size=0.2, random_state=42)

#Declaring Predictor model names
ridge = Ridge()
lasso = Lasso()
linear = LinearRegression()

#Declartion of Ridge Regression things
alpha_values = {
    'alpha' : [0.1, 1.0, 10.0, 20.0, 50.0, 100.0]
    }

grid_search_ridge = GridSearchCV(ridge, alpha_values, cv=5)
grid_search_ridge.fit(X_train, y_train)

print("Best Ridge alpha value:", grid_search_ridge.best_params_)

best_model_ridge = grid_search_ridge.best_estimator_
ridge_y_pred = best_model_ridge.predict(X_test)

ridge_r2 = r2_score(y_test, ridge_y_pred)
ridge_rmse = np.sqrt(mean_squared_error(y_test, ridge_y_pred))

print("Best Ridge R^2: ", ridge_r2)
print("Best Ridge RMSE: ", ridge_rmse)

#Definition of Lasso regression things
grid_search_lasso = GridSearchCV(lasso, alpha_values, cv=5)
grid_search_lasso.fit(X_train, y_train)

print("\nBest Lasso alpha value:", grid_search_lasso.best_params_)

best_model_lasso = grid_search_lasso.best_estimator_
lasso_y_pred = best_model_lasso.predict(X_test)

lasso_r2 = r2_score(y_test, lasso_y_pred)
lasso_rmse = np.sqrt(mean_squared_error(y_test, lasso_y_pred))

print("Best Lasso R^2: ", lasso_r2)
print("Best Lasso RMSE: ", lasso_rmse)

#Linear model setup
linear.fit(X_scaled, y)
y_predicted = linear.predict(X_test)

r2 = r2_score(y_test, y_predicted)
linear_rmse = np.sqrt(mean_squared_error(y_test, y_predicted))
print("\nLinear R^2 Value: ", r2)
print("Linear RMSE: ", linear_rmse)

#Matplotlib section
lasso_coefs = best_model_lasso.coef_
ridge_coefs = best_model_ridge.coef_
linear_coefs = linear.coef_

#Plotting the Coefficients Values against their indicies
plt.figure(figsize=(10, 6))
plt.scatter(range(len(lasso_coefs)), lasso_coefs, color='blue', label="Lasso", marker='o',)
plt.scatter(range(len(ridge_coefs)), ridge_coefs, color='green', label="Ridge", marker='x')
plt.scatter(range(len(linear_coefs)), linear_coefs, color='red', label="Linear", marker='*')
plt.legend(loc='lower right')
plt.xlabel('Coefficient Index')
plt.ylabel('Coefficient Value')
plt.title('Lasso Vs. Ridge Vs. Linear Regression coefficients')
plt.grid(True)
plt.show()

print("\n\n\n\nClassification Task: \n")

#--------------------------------------------------------------------------
#Classification Task
arr = np.loadtxt("data_banknote_authentication.csv", delimiter=',', dtype=str)

features = arr[0, :-1]
target_value = arr[0,-1]

X = arr[1:, :-1]
X = X.astype(float)
y = arr[1:, -1]
y = y.astype(float)
real_estate = arr[1:, 1:]

# Meet the Data Section
print(f'Feature Count: {len(features)}')
print(f'Feature names: {features}')
print(f'Name of target: {target_value}')
print(f'Number of samples: {len(y)}')
print("Description of data: This data include features and target values regarding to classification of counterfeit vs real dollars.")
print(f'First 5 Rows of data: \n {X[:5]}')

#Creating the correlation matrix
df = pd.DataFrame(data = X, columns=['variance of Wavelet Transformed image (continuous)', 'skewness of Wavelet Transformed image (continuous)', 'curtosis of Wavelet Transformed image (continuous)', 'entropy of image (continuous)',])
corr_matrix = df.corr()
print(f'\n{corr_matrix}\n')

#Splitting the Data
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

#Scaling the Data
scaler = StandardScaler()

X_train_scaled = scaler.fit_transform(X_train)
X_test_scaled = scaler.fit_transform(X_test)

#Training Logistic Regression Model
logistic_reg = LogisticRegression()
logistic_reg.fit(X_train_scaled, y_train)

y_pred = logistic_reg.predict(X_test_scaled)

# Evaluate the model
accuracy = logistic_reg.score(X_test_scaled, y_test)
precision = precision_score(y_test, y_test)
recall = recall_score(y_test, y_test)
conf_matrix = confusion_matrix(y_test, y_test)

# Sensitivity (True Positive Rate) = TP / (TP + FN)
sensitivity = conf_matrix[1, 1] / (conf_matrix[1, 1] + conf_matrix[1, 0])

print(f'Precision on the test set: {precision:.2f}')
print(f'Recall on the test set: {recall:.2f}')
print(f'Sensitivity on the test set: {sensitivity:.2f}')
print(f'Accuracy on the test set: {accuracy:.2f}')

# Obtain predicted probabilities for the positive class
y_probabilities = logistic_reg.predict_proba(X_test_scaled)[:, 1]

# Calculate ROC curve
fpr, tpr, thresholds = roc_curve(y_test, y_probabilities)

# Calculate AUC
roc_auc = auc(fpr, tpr)

best_point = tpr - fpr
best_index = np.argmax(best_point)

# Plot ROC curve with No Skill and Best Point
plt.figure(figsize=(10, 6))
plt.plot(fpr, tpr, color='orange', lw=2, label='Logistic')
plt.plot([0, 1], [0, 1], color='blue', lw=2, linestyle='--', label='No Skill')
plt.plot(fpr[best_index], tpr[best_index], marker='o', color='black', label='Best Point')
plt.xlim([0.0, 1.0])
plt.ylim([0.0, 1.05])
plt.xlabel('False Positive Rate')
plt.ylabel('True Positive Rate')
plt.title('Receiver Operating Characteristic (ROC)')
plt.legend(loc="lower right")
plt.grid(True)
plt.show()

