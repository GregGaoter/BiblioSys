import React, { useEffect, useRef } from "react";
import { useAppDispatch } from "../app/store/hooks";
import {
  entities as livreEntities,
  getEntitiesByRayonId as getLivresByRayonId,
  loading as livreLoading,
} from "../app/store/slice/LivreSlice";

export const LivresResultat =()=>{
  const dispatch = useAppDispatch();

  const hasLivres = useRef(false);

  useEffect(() => {
    if (!hasLivres.current) {
      dispatch(getLivresByRayonId(id));
      hasLivres.current = true;
    }
  }, [dispatch]);
}