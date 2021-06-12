import { createSlice } from "@reduxjs/toolkit";
import axios from "axios";
import { defaultValue, ILivre } from "../../model/LivreModel";
import type { AppThunk, RootState } from "../store";

interface LivreState {
  entity: ILivre;
  entities: ReadonlyArray<ILivre>;
  loading: boolean;
  updating: boolean;
  updateSuccess: boolean;
  errorMessage: string | undefined;
}

const initialState: Readonly<LivreState> = {
  entity: defaultValue,
  entities: [],
  loading: false,
  updating: false,
  updateSuccess: false,
  errorMessage: undefined,
};

// Reducer

const livreSlice = createSlice({
  name: "livre",
  initialState,
  reducers: {
    requestGetEntityEntities: (state) => {
      state.loading = true;
      state.updateSuccess = false;
      state.errorMessage = undefined;
    },
    requestCreateUpdateDeleteEntity: (state) => {
      state.updating = true;
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
    },
    successCreateUpdateEntity: (state, action) => {
      state.entity = action.payload.data;
      state.updating = false;
      state.updateSuccess = true;
    },
    successDeleteEntity: (state) => {
      state.entity = defaultValue;
      state.updating = false;
      state.updateSuccess = true;
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

export const baseUrl = "/livre";

const {
  requestGetEntityEntities,
  requestCreateUpdateDeleteEntity,
  failure,
  successGetEntity,
  successGetEntities,
  successCreateUpdateEntity,
  successDeleteEntity,
  resetState,
} = livreSlice.actions;

// Actions

export const getEntity =
  (id: number): AppThunk =>
  async (dispatch: any) => {
    dispatch(requestGetEntityEntities);
    try {
      await axios.get<ILivre>(`${baseUrl}/${id}`).then((response) => dispatch(successGetEntity(response)));
    } catch (e) {
      dispatch(failure(e.message));
    }
  };

export const getEntities = (): AppThunk => async (dispatch: any) => {
  dispatch(requestGetEntityEntities);
  try {
    await axios.get<ILivre>(`${baseUrl}/all`).then((response) => dispatch(successGetEntities(response)));
  } catch (e) {
    dispatch(failure(e.message));
  }
};

export const getEntitiesByRayonId =
  (id: number): AppThunk =>
  async (dispatch: any) => {
    dispatch(requestGetEntityEntities);
    try {
      await axios.get<ILivre>(`${baseUrl}/rayon/${id}`).then((response) => dispatch(successGetEntities(response)));
    } catch (e) {
      dispatch(failure(e.message));
    }
  };

export const getEntitiesByGenreId =
  (id: number): AppThunk =>
  async (dispatch: any) => {
    dispatch(requestGetEntityEntities);
    try {
      await axios.get<ILivre>(`${baseUrl}/genre/${id}`).then((response) => dispatch(successGetEntities(response)));
    } catch (e) {
      dispatch(failure(e.message));
    }
  };

export const createEntity =
  (entity: ILivre): AppThunk =>
  async (dispatch: any) => {
    dispatch(requestCreateUpdateDeleteEntity);
    try {
      const response = await axios.post<ILivre>(`${baseUrl}`, entity);
      dispatch(successCreateUpdateEntity(response));
      await dispatch(getEntities());
      return response;
    } catch (e) {
      dispatch(failure(e.message));
    }
  };

export const updateEntity =
  (id: number, entity: ILivre): AppThunk =>
  async (dispatch: any) => {
    dispatch(requestCreateUpdateDeleteEntity);
    try {
      await axios
        .put<ILivre>(`${baseUrl}/${id}`, entity)
        .then((response) => dispatch(successCreateUpdateEntity(response)));
    } catch (e) {
      dispatch(failure(e.message));
    }
  };

export const deleteEntity =
  (id: number): AppThunk =>
  async (dispatch: any) => {
    dispatch(requestCreateUpdateDeleteEntity);
    try {
      const response = await axios.delete<ILivre>(`${baseUrl}`);
      dispatch(successDeleteEntity);
      await dispatch(getEntities());
      return response;
    } catch (e) {
      dispatch(failure(e.message));
    }
  };

export const reset = () => (dispatch: any) => {
  dispatch(resetState);
};

// Selectors

export const entity = (state: RootState) => state.livre.entity;
export const entities = (state: RootState) => state.livre.entities;
export const loading = (state: RootState) => state.livre.loading;
export const updating = (state: RootState) => state.livre.updating;
export const updateSuccess = (state: RootState) => state.livre.updateSuccess;
export const errorMessage = (state: RootState) => state.livre.errorMessage;

export default livreSlice.reducer;
