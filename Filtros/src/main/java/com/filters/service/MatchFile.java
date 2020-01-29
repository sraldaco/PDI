package com.filters.service;

import java.util.List;

public class MatchFile {
    private static final List<StoredImage> storedImages = Store.getFileStoredList();
    /*
     * @param elements es el arreglo que contiene los elementos
     * @param findElement es el elemento a encontrar
     * @return resultado de la búsqueda binaria del elemento
     */
    public static String Search(CoordsColor findElement) {
        int distance = findElement.getDistance();
        int low = 0, high = storedImages.size() - 1;
        return binarySearch(findElement, distance, low, high);
    }
    /*
     * @param elements es el arreglo que contiene los elementos
     * @param findElement es el elemento a encontrar
     * @param low inicio del intervalo de búsqueda
     * @param high final del intervalo de búsqueda
     * @return posición del elemento ó -1 si no se encontró
     */
    private static String binarySearch(CoordsColor findElement, int distance, int low, int high) {
        while (low <= high) {
            int medium = (low + high) / 2;
            if (storedImages.get(medium).getCoords().getDistance() < distance) low = medium + 1;
            else if (storedImages.get(medium).getCoords().getDistance() > distance) high = medium - 1;
            else break;
        }
        low = Math.max(low - 100, 0);
        high = Math.min(high + 100, storedImages.size() -1);
        StoredImage min = storedImages.get(low);
        double minDistance = min.getCoords().getDistance(findElement);
        for (int i = low + 1; i <= high; i++) {
            StoredImage current = storedImages.get(i);
            double currentDistance = current.getCoords().getDistance(findElement);
            if (currentDistance < minDistance) {
                min = current;
                minDistance = currentDistance;
            }
        }
        return min.getFilename();
    }
}
