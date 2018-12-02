package service;

import entity.DistrictsEntity;

public interface DistrictsService {
   /**
    * find district by id
    * @param districtId id
    * @return district
    */
   DistrictsEntity getDistrictById(int districtId);

   /**
    * It creates new district
    * @param district district
    * @return new district
    */
   DistrictsEntity createDistrict(DistrictsEntity district);

   /**
    * It updates the district
    * @param district district
    * @return district if it was correctly updated
    */
   DistrictsEntity updateDistrict(DistrictsEntity district);

   /**
    * It deletes the district
    * @param district district
    * @return true if district was successfully deleted
    */
   boolean deleteDistrict(DistrictsEntity district);
}
