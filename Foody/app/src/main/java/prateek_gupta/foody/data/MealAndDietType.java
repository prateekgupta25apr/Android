package prateek_gupta.foody.data;

public class MealAndDietType {
    public String selectedMealType;
    public Integer selectedMealTypeId;
    public String selectedDietType;
    public Integer selectedDietTypeId;

    public MealAndDietType(String selectedMealType, Integer selectedMealTypeId,
                           String selectedDietType, Integer selectedDietTypeId) {
        this.selectedMealType = selectedMealType;
        this.selectedMealTypeId = selectedMealTypeId;
        this.selectedDietType = selectedDietType;
        this.selectedDietTypeId = selectedDietTypeId;
    }

    public String getSelectedMealType() {
        return selectedMealType;
    }

    public void setSelectedMealType(String selectedMealType) {
        this.selectedMealType = selectedMealType;
    }

    public Integer getSelectedMealTypeId() {
        return selectedMealTypeId;
    }

    public void setSelectedMealTypeId(Integer selectedMealTypeId) {
        this.selectedMealTypeId = selectedMealTypeId;
    }

    public String getSelectedDietType() {
        return selectedDietType;
    }

    public void setSelectedDietType(String selectedDietType) {
        this.selectedDietType = selectedDietType;
    }

    public Integer getSelectedDietTypeId() {
        return selectedDietTypeId;
    }

    public void setSelectedDietTypeId(Integer selectedDietTypeId) {
        this.selectedDietTypeId = selectedDietTypeId;
    }
}
