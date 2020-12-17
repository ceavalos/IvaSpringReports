package innotec.com.sv.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import innotec.com.sv.dao.DeclaracionDao;
import innotec.com.sv.dao.IComprasDao;
import innotec.com.sv.entities.Compra;
import innotec.com.sv.entities.Declaracion;
import innotec.com.sv.entities.Empresa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@Service
public class DeclaracionServiceImp implements DeclaracionService {

	@Autowired
	DeclaracionDao declaracionDao;
	
	@Autowired
	IComprasDao comprasDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Declaracion> findAll() {		
		return (List<Declaracion>) declaracionDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Page<Declaracion> findAll(Pageable pageable) {	
		return declaracionDao.findAll(pageable);
	}

	@Override
	@Transactional
	public void save(Declaracion declaracion) {
		declaracionDao.save(declaracion);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Declaracion findOne(Long id) {		
		return declaracionDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		declaracionDao.deleteById(id);		
	}

	@Override
	public List<Declaracion> findEmpresa(Empresa empresa) {		
		return declaracionDao.findByEmpresa(empresa);
	}

	@Override
	public double totalCompras(Declaracion declaracion) {
		List<Compra> compras = comprasDao.findByDeclaracion(declaracion.getId());
		double total = 0; 
		
		for (int i =0; i<=compras.size(); i++) {
			total += compras.get(i).getTotal_compras();
		}		
		return total;
	}

	@Override
	public double totalVentas(Declaracion declaracion) {
		// TODO Auto-generated method stub
		return 0;
	}

}
