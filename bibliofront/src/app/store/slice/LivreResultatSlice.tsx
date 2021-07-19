import { createSlice } from "@reduxjs/toolkit";
import axios, { AxiosResponse } from "axios";
import Constants from "../../Constants";
import { LivreResultatFilter } from "../../model/enumeration/LivreResultatFilter";
import { defaultValue, ILivreResultat } from "../../model/LivreResultatModel";
import type { AppThunk, RootState } from "../store";

interface LivreResultatState {
  entity: ILivreResultat;
  entities: ReadonlyArray<ILivreResultat>;
  loading: boolean;
  updating: boolean;
  updateSuccess: boolean;
  errorMessage: string | undefined;
  totalItems: number;
  filter: LivreResultatFilter | undefined;
  filterId: number | undefined;
}

const initialState: Readonly<LivreResultatState> = {
  entity: defaultValue,
  entities: [],
  loading: false,
  updating: false,
  updateSuccess: false,
  errorMessage: undefined,
  totalItems: 0,
  filter: undefined,
  filterId: undefined,
};

// Reducer

const livreResultatSlice = createSlice({
  name: "livreResultat",
  initialState,
  reducers: {
    requestGetEntityEntities: (state) => {
      state.loading = true;
      state.updateSuccess = false;
      state.errorMessage = undefined;
    },
    failure: (state, action) => {
      state.loading = false;
      state.updating = false;
      state.updateSuccess = false;
      state.errorMessage = action.payload;
    },
    successGetEntity: (state, action) => {
      state.entity = action.payload.data;
      state.loading = false;
    },
    successGetEntities: (state, action) => {
      state.entities = action.payload.data;
      state.loading = false;
      state.totalItems = parseInt(action.payload.headers[Constants.TOTAL_COUNT_HEADER], 10);
      state.filter = undefined;
      state.filterId = undefined;
    },
    successGetEntitiesByRayonId: (state, action) => {
      state.entities = action.payload.data;
      state.loading = false;
      state.totalItems = parseInt(action.payload.headers[Constants.TOTAL_COUNT_HEADER], 10);
      state.filter = LivreResultatFilter.BY_RAYON_ID;
      state.filterId = parseInt(action.payload.headers[Constants.FILTER_ID_HEADER], 10);
    },
    successGetEntitiesByGenreId: (state, action) => {
      state.entities = action.payload.data;
      state.loading = false;
      state.totalItems = parseInt(action.payload.headers[Constants.TOTAL_COUNT_HEADER], 10);
      state.filter = LivreResultatFilter.BY_GENRE_ID;
      state.filterId = parseInt(action.payload.headers[Constants.FILTER_ID_HEADER], 10);
    },
    resetState: (state) => {
      state.entity = defaultValue;
      state.entities = [];
      state.loading = false;
      state.updating = false;
      state.updateSuccess = false;
      state.errorMessage = undefined;
    },
  },
});

export const baseUrl = "/livre/resultat";

const {
  requestGetEntityEntities,
  failure,
  successGetEntity,
  successGetEntities,
  successGetEntitiesByRayonId,
  successGetEntitiesByGenreId,
  resetState,
} = livreResultatSlice.actions;

// Actions

export const getEntity =
  (id: number): AppThunk =>
  async (dispatch: any) => {
    dispatch(requestGetEntityEntities);
    try {
      await axios.get<ILivreResultat>(`${baseUrl}/${id}`).then((response) => dispatch(successGetEntity(response)));
    } catch (e) {
      dispatch(failure(e.message));
    }
  };

export const getEntities = (): AppThunk => async (dispatch: any) => {
  dispatch(requestGetEntityEntities);
  try {
    await axios.get<ILivreResultat>(`${baseUrl}/all`).then((response) => dispatch(successGetEntities(response)));
  } catch (e) {
    dispatch(failure(e.message));
  }
};

export const getEntitiesByRayonId =
  (id: number, first: number, size: number): AppThunk =>
  async (dispatch: any) => {
    dispatch(requestGetEntityEntities);
    try {
      await axios
        .get<ILivreResultat>(`${baseUrl}/rayon/${id}?first=${first}&size=${size}`)
        .then((response) => dispatch(successGetEntitiesByRayonId(response)));
    } catch (e) {
      dispatch(failure(e.message));
    }
  };

export const getEntitiesByGenreId =
  (id: number, first: number, size: number): AppThunk =>
  async (dispatch: any) => {
    dispatch(requestGetEntityEntities);
    try {
      await axios
        .get<ILivreResultat>(`${baseUrl}/genre/${id}?first=${first}&size=${size}`)
        .then((response) => dispatch(successGetEntitiesByGenreId(response)));
    } catch (e) {
      dispatch(failure(e.message));
    }
  };

export const reset = () => (dispatch: any) => {
  dispatch(resetState);
};

// Selectors

export const entity = (state: RootState) => state.livreResultat.entity;
export const entities = (state: RootState) => state.livreResultat.entities;
export const loading = (state: RootState) => state.livreResultat.loading;
export const updating = (state: RootState) => state.livreResultat.updating;
export const updateSuccess = (state: RootState) => state.livreResultat.updateSuccess;
export const errorMessage = (state: RootState) => state.livreResultat.errorMessage;
export const totalItems = (state: RootState) => state.livreResultat.totalItems;
export const filter = (state: RootState) => state.livreResultat.filter;
export const filterId = (state: RootState) => state.livreResultat.filterId;

export default livreResultatSlice.reducer;
