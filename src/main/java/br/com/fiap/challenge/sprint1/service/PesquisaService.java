package br.com.fiap.challenge.sprint1.service;

import br.com.fiap.challenge.sprint1.infra.ValidacaoException;
import br.com.fiap.challenge.sprint1.model.categoria.Categoria;
import br.com.fiap.challenge.sprint1.model.figuraPublica.FiguraPublica;
import br.com.fiap.challenge.sprint1.model.pesquisa.Pesquisa;
import br.com.fiap.challenge.sprint1.model.pesquisa.dto.AdicionarFigPublicaDTO;
import br.com.fiap.challenge.sprint1.model.pesquisa.dto.CriarPesquisaDTO;
import br.com.fiap.challenge.sprint1.model.tipoServico.TipoServico;
import br.com.fiap.challenge.sprint1.repository.PesquisaRepository;
import br.com.fiap.challenge.sprint1.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PesquisaService {

    @Autowired
    private PesquisaRepository pesquisaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Pesquisa criarPesquisa(Long idUsuario, CriarPesquisaDTO dto){
        if(!usuarioRepository.existsById(idUsuario)){
            throw new ValidacaoException("O usuario não existe!");
        }
        var usuario = usuarioRepository.getReferenceById(idUsuario);
        var pesquisa = new Pesquisa(dto);
        var tipServico = new TipoServico(dto);
        var figuraPub = new FiguraPublica(dto);
        var categoria = new Categoria(dto);

        pesquisa.setUsuario(usuario);
        usuario.getPesquisas().add(pesquisa);
        pesquisa.setTipoServico(tipServico);
        pesquisa.getFiguraPublica().add(figuraPub);

        figuraPub.getPesquisa().add(pesquisa);
        figuraPub.setCategoria(categoria);

        pesquisaRepository.save(pesquisa);
        return pesquisa;
    }

    public Pesquisa addFiguraPublica(Long idPesquisa, AdicionarFigPublicaDTO dto){
        if(!pesquisaRepository.existsById(idPesquisa)){
            throw new ValidacaoException("A pesquisa não existe!");
        }
        var pesquisa = pesquisaRepository.getReferenceById(idPesquisa);
        var figPublica = new FiguraPublica(dto);
        var categoria = new Categoria(dto);

        pesquisa.getFiguraPublica().add(figPublica);
        figPublica.setCategoria(categoria);

        return pesquisa;
    }

}
