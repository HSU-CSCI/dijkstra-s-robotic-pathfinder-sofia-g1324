from PIL import Image
import numpy as np

def generate_c_space(grid, robot_radius):
    rows, cols = len(grid), len(grid[0])
    c_space = np.zeros((rows, cols), dtype=int)

    # Iterate over each cell in the grid
    for x in range(rows):
        for y in range(cols):
            # If the cell is an obstacle
            if grid[x][y] == 1:
                # Expand this obstacle based on the robot radius
                for dx in range(-robot_radius, robot_radius + 1):
                    for dy in range(-robot_radius, robot_radius + 1):
                        distance = np.sqrt(dx**2 + dy**2)
                        # If the distance is within the robot's radius, mark as unsafe
                        if distance <= robot_radius:
                            x_new, y_new = x + dx, y + dy
                            # Ensure (x_new, y_new) is within bounds
                            if 0 <= x_new < rows and 0 <= y_new < cols:
                                c_space[x_new][y_new] = 1

    return c_space



def bitmap_to_array(image_path):
    # Open the image using PIL
    image = Image.open(image_path)

    # Convert the image to grayscale (if it's not already)
    grayscale_image = image.convert('L')  # 'L' mode is for grayscale

    # Resize the image to 300x300 if it's not already
    resized_image = grayscale_image.resize((300, 300))

    # Convert the image to a numpy array
    image_array = np.array(resized_image)

    # Create a binary array where 0 = white and 1 = black
    # Assuming a threshold of 128 for distinguishing white from black
    binary_array = np.where(image_array < 128, 1, 0)

    return binary_array


image_path = "./house.bmp"
array = bitmap_to_array(image_path)
cspace = generate_c_space(array, 6)
np.savetxt("./cspace.csv", cspace, delimiter=",", fmt='%d')

