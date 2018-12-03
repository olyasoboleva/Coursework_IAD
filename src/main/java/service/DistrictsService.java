package service;

import entity.District;

public interface DistrictsService {
   /**
    * find district by id
    * @param districtId id
    * @return district
    */
   District getDistrictById(int districtId);

   /**
    * It creates new district
    * @param district district
    * @return new district
    */
   District createDistrict(District district);

   /**
    * It updates the district
    * @param district district
    * @return district if it was correctly updated
    */
   District updateDistrict(District district);

   /**
    * It deletes the district
    * @param district district
    * @return true if district was successfully deleted
    */
   boolean deleteDistrict(District district);
}
