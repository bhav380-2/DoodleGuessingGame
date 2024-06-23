import sys
from PIL import Image
import numpy as np
import tensorflow as tf

def predict(image_path):
    print("*****************************************************")
    model = tf.keras.models.load_model('path_to_your_model.h5')
    image = Image.open(image_path).convert('L')  # Convert to grayscale
    print(image)
    image = image.resize((28, 28))  # Resize to 28x28 if using a model like MNIST
    image = np.array(image)
    image = image / 255.0  # Normalize pixel values
    image = image.reshape(1, 28, 28, 1)  # Reshape for the model

    prediction = model.predict(image)
    predicted_class = np.argmax(prediction)
    class_names = ['class1', 'class2', 'class3']  # Replace with your actual class names

    return class_names[predicted_class]

if __name__ == '__main__':
    image_path = sys.argv[1]
    result = predict(image_path)
    print(result)
